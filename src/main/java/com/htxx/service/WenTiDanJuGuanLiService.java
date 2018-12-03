package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.DlDianfei;
import com.htxx.entity.DlDianfeiDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: SaHongzhi
 * @Date: 2018-10-30 15:14
 */
public interface WenTiDanJuGuanLiService {

    public void export(HttpServletRequest request, HttpServletResponse response,  String ids, Map map);


    BaseResultData saveOrEditWenTiDanJu(Integer isAdd, DlDianfeiDetail dlDianfeiDetail, DlDianfei dlDianfei, HttpSession session);

    BaseResultData wenTiDanJuHeBing(String id, String userName, String minMoneyIncludeTax, String maxMoneyIncludeTax, String taxNum, String remark, String date,String dateType);
}
