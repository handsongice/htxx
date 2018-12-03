package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Ykfp;
import com.htxx.entity.xxfp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-07 16:08
 */
public interface ZiYouPiaoService {

    BaseResultData saveZiYouPiao(xxfp xxfp);

}
