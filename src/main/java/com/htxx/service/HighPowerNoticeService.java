package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Gfxx;
import com.htxx.entity.Notice;
import com.htxx.entity.PageMsg;
import com.htxx.entity.Spxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 高可靠通知单 service
 *
 * @author ll
 * @date 2018-10-25
 */
public interface HighPowerNoticeService {
    /**
     * 高可靠通知单 类表获取
     *
     * @param pageMsg page 分页信息
     * @param notice  通知单实体
     * @return
     */
    PageMsg<Notice> getlist(PageMsg<Notice> pageMsg, Notice notice);

    /**
     * 通知单导出
     *
     * @param request  servlet请求
     * @param response servlet响应
     * @param pageMsg  页面数据
     * @param notice   页面的通知单查询条件
     * @param type     导出类型  按条件导出/CheckBox批量导出 单条导出
     */
    void noticesExport(HttpServletRequest request, HttpServletResponse response, PageMsg<Notice> pageMsg, Notice notice, int type);

    /**
     * 通知单删除
     *
     * @param request servlet请求
     * @return
     */
    ResultMap deleteNotices(HttpServletRequest request);

    /**
     * 通知单提交
     *
     * @param request servlet请求
     * @param pageMsg 页面数据
     * @param notice  页面的通知单查询条件
     * @param type    提交类型  按条件提交/CheckBox批量提交 单条提交
     * @return
     */
    ResultMap noticesSubmit(HttpServletRequest request, PageMsg<Notice> pageMsg, Notice notice, int type);

    /**
     * 通知单添加
     *
     * @param notice
     * @return
     */
    ResultMap addNotice(HttpServletRequest request, Notice notice);

    /**
     * 通知单 编辑更新
     *
     * @param notice
     * @return
     */
    ResultMap updateNotice(Notice notice);
}
