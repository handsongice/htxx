package com.htxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.mapper.EnterpriseMapper;
import com.htxx.mapper.NoticeMapper;
import com.htxx.mapper.SpxxMapper;
import com.htxx.service.CommonService;
import com.htxx.service.HighPowerNoticeService;
import com.htxx.util.BigDecimalUtil;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高可靠通知单 serviceImpl
 *
 * @author ll
 * @date 2018-10-25
 */
@Slf4j
@Service
@Transactional
public class HighPowerNoticeServiceImpl implements HighPowerNoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CommonService commonService;

    @Override
    public PageMsg<Notice> getlist(PageMsg<Notice> pageMsg, Notice notice) {
        try {
            log.info("pageMsg:" + pageMsg);
            log.info("Notice:" + notice);
            //传入起始页与页数大小
            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
            pageMsg.setEntity(notice);
            //带条件查询
            List<Notice> all = noticeMapper.getNoticeList(pageMsg);
            //将用户数据封装到PageInfo 中
            PageInfo<Notice> page = new PageInfo(all);
            //设置成功代码
            pageMsg.setCode("0");
            //设置查询数据
            pageMsg.setData(all);
            //设置数据数量 return result;
            pageMsg.setCount(page.getTotal());
            return pageMsg;
        } catch (Exception e) {
            log.error("HighPowerNoticeServiceImpl.getlist捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public void noticesExport(HttpServletRequest request, HttpServletResponse response, PageMsg<Notice> pageMsg, Notice notice, int type) {
        try {
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String now = df.format(new Date());
            //创建文件名称
            String fileName = "高可靠电费通知单" + now + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, request, response);
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"户名", "账号", "工作单号", "金额", "缴费标准", "状态", "创建时间"};
            String[] cols = {"hm", "zh", "gzdh", "je", "jfbz", "status", "createTime"};
            // 通知单列表
            List<Notice> list;
            if (Constent.SIGN_INT_0 == type) {
                //通知单按条件导出
                //pageMsg对象  并设置实体 通知单的查询条件
                pageMsg.setEntity(notice);
                log.info("pageMsg:" + pageMsg);
                //获取通知单所有列表
                list = noticeMapper.getNoticeList(pageMsg);
            } else {
                //通知单checkbox批量导出
                String ids = request.getParameter("ids");
                String[] tzdIds = ids.split(",");
                list = noticeMapper.getNoticeListById(tzdIds);
            }
            // 导出时 状态字段 0,1  变成“未提交”“已提交”
            list.forEach(item -> {
                if (Constent.SIGN_STRING_1.equals(item.getStatus())) {
                    item.setStatus("未提交");
                } else {
                    item.setStatus("已提交");
                }
            });
            log.info("list:" + list);
            //构造excel导出类
            PoiExcelExportUtil<Notice> pee = new PoiExcelExportUtil(fileName, heads, cols, list, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("HighPowerNoticeServiceImpl.noticesExport捕获异常！" + e);
        }
    }

    @Override
    public ResultMap deleteNotices(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String[] tzdIds = ids.split(",");
        int deleteResult = noticeMapper.deleteNotices(tzdIds);
        if (deleteResult > 0) {
            return ResultMap.success(Constent.DB_DELETE_SUCCESS);
        } else {
            throw new RuntimeException(Constent.DB_DELETE_FAILURE);
        }
    }

    @Override
    public ResultMap noticesSubmit(HttpServletRequest request, PageMsg<Notice> pageMsg, Notice notice, int type) {
        // 数据库更新结果
        int updateResult = 0;
        if (Constent.SIGN_INT_0 == type) {
            //通知单按条件提交
            //pageMsg对象  并设置实体 通知单的查询条件
            pageMsg.setEntity(notice);
            log.info("pageMsg:" + pageMsg);
            //获取通知单所有列表
            updateResult = noticeMapper.updateSubmitStatusByCondition(pageMsg);
        } else {
            //通知单checkbox批量提交 单条提交
            String ids = request.getParameter("ids");
            String[] tzdIds = ids.split(",");
            updateResult = noticeMapper.updateSubmitStatusById(tzdIds);
        }
        if (updateResult > 0) {
            return ResultMap.success(Constent.DB_SUBMIT_SUCCESS);
        } else {
            throw new RuntimeException(Constent.DB_SUBMIT_FAILURE);
        }
    }

    @Override
    public ResultMap addNotice(HttpServletRequest request, Notice notice) {
        //获取session中的操作员信息
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute(Constent.SESSION_EMPLOYEE);
        //获取操作员对应的企业信息
        Enterprise enterprise = (Enterprise) session.getAttribute(Constent.SESSION_ENTERPRISE);
        //获取高可靠电费对应商品信息
        Map<String,Object> map = new HashMap<>();
        map.put("ywlx",Constent.SIGN_INT_2);
        Spxx spxx = commonService.getSpxxListByYwlx(map).get(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        notice.setCreateTime(df.format(new Date()));
        //设置操作人
        notice.setEmployeeId(employee.getId());
        //设置 开票人 收款人 复核人
        notice.setKpr(employee.getName());
        notice.setSkr(employee.getName());
        notice.setFhr(employee.getName());
        //设置企业ID
        notice.setEnterpriseId(enterprise.getId());
        //设置销方信息  名称  税号  地址电话  银行账号
        notice.setXfmc(enterprise.getName());
        notice.setXfsh(enterprise.getTaxNum());
        notice.setXfdzdh(enterprise.getAddr());
        notice.setXfyhzh(enterprise.getBankInfo());
        //设置合计税额
        notice.setHjse(BigDecimalUtil.sub(notice.getJe()- notice.getJe()/ 1.1));
        //设置清单标志 -0  非清单
        notice.setQdbz(Constent.SIGN_INT_0);
        //设置业务类型 - 2  高可靠电费收入
        notice.setYwlx(Constent.SIGN_STRING_2);
        //设置发票种类 - 1  正常蓝票
        notice.setFpzl(Constent.SIGN_STRING_1);
        //创建单据明细信息
        YkfpDel noticeDel = new YkfpDel();
        //设置明细 单据号
        noticeDel.setDjh(notice.getGzdh());
        //设置明细  含税标志-1  含税
        noticeDel.setHsbz(Constent.SIGN_INT_1);
        //设置明细  税率(高可靠电费) 10%
        noticeDel.setSl(Double.parseDouble(spxx.getSl()));
        //设置明细  商品名称
        noticeDel.setXmmc(spxx.getSpmc());
        //设置明细  序号
        noticeDel.setXh(Constent.SIGN_INT_1);
        //设置明细  数量 默认 1
        noticeDel.setXmsl((double) Constent.SIGN_INT_1);
        //设置明细  税收分类编码
        noticeDel.setShflbm(spxx.getShflbm());
        //设置明细  含税金额
        noticeDel.setHsje(notice.getJe());
        //设置明细  含税单价
        noticeDel.setHsdj(notice.getJe());
        //设置明细  税额
        noticeDel.setSe(BigDecimalUtil.sub(notice.getJe()- notice.getJe()/ 1.1));
        int dbResult = noticeMapper.insertNotice(notice);
        int dbResult2 = noticeMapper.insertNoticeDel(noticeDel);
        if (dbResult > 0 && dbResult2 > 0) {
            return ResultMap.success(Constent.DB_INSERT_SUCCESS);
        } else {
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
    }

    @Override
    public ResultMap updateNotice(Notice notice) {
        //设置更新的  税额
        notice.setHjse(BigDecimalUtil.sub(notice.getJe()- notice.getJe()/ 1.1));
        int dbResult = noticeMapper.updateNoticeById(notice);
        if (dbResult > 1) {
            return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
        } else {
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
    }

}
