package com.htxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.entity.Notice;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;
import com.htxx.mapper.YkfpMapper;
import com.htxx.service.HighPowerYkfpMngService;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ll on 2018-11-01
 * 高可靠 已开发票 service impl
 */
@Service
@Slf4j
public class HighPowerYkfpMngServiceImpl implements HighPowerYkfpMngService {
    @Autowired
    private YkfpMapper ykfpMapper;

    @Override
    public PageMsg<Ykfp> getList(PageMsg<Ykfp> pageMsg, Ykfp ykfp) {
        try {
            log.info("Ykfp:" + ykfp);
            //传入起始页与页数大小
            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
            pageMsg.setEntity(ykfp);
            //带条件查询
            List<Ykfp> all = ykfpMapper.getYkfpMainList(pageMsg);
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
            log.error("HighPowerYkfpMngServiceImpl.getlist捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public void ykfpExport(HttpServletRequest request, HttpServletResponse response, PageMsg<Ykfp> pageMsg, Ykfp ykfp, int type) {
        try {
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String now = df.format(new Date());
            //创建文件名称
            String fileName = "高可靠电费发票信息" + now + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, request, response);
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"单据号", "发票代码", "发票号码", "开票时间", "合计税额", "合计金额", "价税合计", "购方名称", "购方税号", "购方地址电话", "购方银行账号", "销方名称", "销方税号", "销方地址电话", "销方银行账号", "开票机", "发票类型", "发票状态", "开票人", "收款人", "复核人", "备注", "原发票代码", "原发票号码", "税收分类编码版本号"};
            String[] cols = {"djh", "fpdm", "fphm", "kpsj", "hjse", "hjje", "jshj", "gfmc", "gfsh", "gfdzdh", "gfyhzh", "xfmc", "xfsh", "xfdzdh", "xfyhzh", "nickName", "fpzl", "kplx", "kpr", "skr", "fhr", "bz", "yfpdm", "yfphm", "bmbbh"};
            // 通知单列表
            List<Ykfp> list;
            if (Constent.SIGN_INT_0 == type) {
                //已开发票按条件导出
                //pageMsg对象  并设置实体 已开发票的查询条件
                pageMsg.setEntity(ykfp);
                log.info("pageMsg:" + pageMsg);
                //获取已开发票所有列表
                list = ykfpMapper.getYkfpMainAndDelListByCondition(pageMsg);
            } else {
                //通知单checkbox批量导出
                String djhStr = request.getParameter("djhs");
                String[] djhs = djhStr.split(",");
                list = ykfpMapper.getYkfpMainAndDelListByDjh(djhs);
            }
            // 导出时 分机号，发票类型,发票状态 字段 数字标识变文字描述
            list.forEach(item -> {
                // 分机号
//                if (Constent.SIGN_STRING_0.equals(item.getFjh())) {
//                    item.setFjh("主机");
//                } else {
//                    item.setFjh("分机" + item.getFjh());
//                }
                //发票类型
                if (Constent.SIGN_STRING_0.equals(item.getFpzl())) {
                    item.setFpzl("专票");
                } else if (Constent.SIGN_STRING_2.equals(item.getFpzl())) {
                    item.setFpzl("普票");
                } else if (Constent.SIGN_STRING_41.equals(item.getFpzl())) {
                    item.setFpzl("卷票");
                } else if (Constent.SIGN_STRING_51.equals(item.getFpzl())) {
                    item.setFpzl("电子票");
                }
                //发票状态
                if (Constent.SIGN_STRING_1.equals(item.getKplx())) {
                    item.setKplx("正常");
                } else if (Constent.SIGN_STRING_2.equals(item.getKplx())) {
                    item.setKplx("红票");
                } else if (Constent.SIGN_STRING_3.equals(item.getKplx())) {
                    item.setKplx("被冲红");
                }else if(Constent.SIGN_STRING_4.equals(item.getKplx()) || Constent.SIGN_STRING_5.equals(item.getKplx())){
                    item.setKplx("废票");
                }
            });
            log.info("list:" + list);
            //构造excel导出类
            PoiExcelExportUtil<Ykfp> pee = new PoiExcelExportUtil(fileName, heads, cols, list, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("HighPowerYkfpMngServiceImpl.ykfpExport捕获异常！");
        }
    }
}
