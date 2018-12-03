package com.htxx.service;

import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ll on 2018-11-01
 * 高可靠 -- 已开发票  service
 */
public interface HighPowerYkfpMngService {
    /**
     * 高可靠 已开发票列表获取 带分页
     *
     * @param pageMsg page 分页信息
     * @param ykfp    已开发票实体
     * @return
     */
    PageMsg<Ykfp> getList(PageMsg<Ykfp> pageMsg, Ykfp ykfp);

    /**
     * 高可靠 已开发票列表 导出至excel
     * @param request
     * @param response
     * @param pageMsg
     * @param ykfp
     * @param type
     */
    void ykfpExport(HttpServletRequest request, HttpServletResponse response, PageMsg<Ykfp> pageMsg, Ykfp ykfp, int type);
}
