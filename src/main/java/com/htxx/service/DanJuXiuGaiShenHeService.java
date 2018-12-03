package com.htxx.service;

import com.htxx.common.BaseResultData;

import javax.servlet.http.HttpSession; /**
 * @Author: SaHongzhi
 * @Date: 2018-11-28 11:09
 */
public interface DanJuXiuGaiShenHeService {
    BaseResultData submitSuggestion(int shyj, String suggestion, long mainId, HttpSession session);
}
