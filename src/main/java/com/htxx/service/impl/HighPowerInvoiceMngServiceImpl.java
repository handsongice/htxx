package com.htxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.entity.Gfxx;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Spxx;
import com.htxx.mapper.GfxxMapper;
import com.htxx.service.CommonService;
import com.htxx.service.HighPowerInvoiceMngService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-10-30
 */
@Service
@Slf4j
@Transactional
public class HighPowerInvoiceMngServiceImpl implements HighPowerInvoiceMngService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private GfxxMapper gfxxMapper;

    @Override
    public PageMsg<Spxx> spxxList(PageMsg<Spxx> pageMsg, Spxx spxx, String ywlx) {
        try {
            log.info("spxxList pageMsg:" + pageMsg);
            log.info("spxx:" + spxx);
            //传入起始页与页数大小
            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
            pageMsg.setEntity(spxx);
            //带条件查询
            Map<String, Object> map = new HashMap<>();
            //业务类型
            map.put("ywlx", ywlx);
            if (null != spxx.getSpmc() && "" != spxx.getSpmc()) {
                map.put("spmc", spxx.getSpmc());
            }
            List<Spxx> all = commonService.getSpxxListByYwlx(map);
            log.info("Spxxlist:" + all);
            //将用户数据封装到PageInfo 中
            PageInfo<Spxx> page = new PageInfo(all);
            //设置成功代码
            pageMsg.setCode("0");
            //设置查询数据
            pageMsg.setData(all);
            //设置数据数量 return result;
            pageMsg.setCount(page.getTotal());
            return pageMsg;
        } catch (Exception e) {
            log.error("HighPowerInvoiceMngServiceImpl.spxxList捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public PageMsg<Gfxx> gfxxList(PageMsg<Gfxx> pageMsg, Gfxx gfxx, String keyword) {
        try {
            log.info("gfxxList pageMsg:" + pageMsg);
            log.info("gfxx:" + gfxx);
            //传入起始页与页数大小
            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
            pageMsg.setEntity(gfxx);
            List<Gfxx> all = gfxxMapper.selectByKeyword(keyword);
            log.info("gfxxList:" + all);
            //将用户数据封装到PageInfo 中
            PageInfo<Gfxx> page = new PageInfo(all);
            //设置成功代码
            pageMsg.setCode("0");
            //设置查询数据
            pageMsg.setData(all);
            //设置数据数量 return result;
            pageMsg.setCount(page.getTotal());
            return pageMsg;
        } catch (Exception e) {
            log.error("HighPowerInvoiceMngServiceImpl.gfxxList捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public void saveYkfpInfo(HttpServletRequest request) {

    }
}
