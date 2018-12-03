package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Gfxx;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Spxx;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ll on 2018-10-30
 */
public interface HighPowerInvoiceMngService {

    /**
     * 商品信息列表
     * @param pageMsg
     * @param spxx
     * @param ywlx 业务类型
     * @return
     */
    PageMsg<Spxx> spxxList(PageMsg<Spxx> pageMsg, Spxx spxx, String ywlx);

    /**
     * 购方信息列表
     * @param pageMsg
     * @param gfxx
     * @return
     */
    PageMsg<Gfxx> gfxxList(PageMsg<Gfxx> pageMsg, Gfxx gfxx, String keyword);

    /**
     * 存储已开发票信息
     * @param request
     */
    void saveYkfpInfo(HttpServletRequest request);
}
