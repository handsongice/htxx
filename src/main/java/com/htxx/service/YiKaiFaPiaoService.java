package com.htxx.service;

import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-07 16:08
 */
public interface YiKaiFaPiaoService {

    void ykfpExport(HttpServletRequest request, HttpServletResponse response, PageMsg<Ykfp> pageMsg, Ykfp ykfp, int type);

}
