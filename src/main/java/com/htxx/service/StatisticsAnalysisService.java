package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.MonthStatisticsData;
import com.htxx.entity.PageMsg;
import com.htxx.entity.TerminalAnalysis;
import com.htxx.entity.Ykfp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ll on 2018-11-24
 * 统计分析模块 service
 */
public interface StatisticsAnalysisService {
    /**
     * 开票机发票使用情况 - 列表数据
     * @param pageMsg
     * @param terminalAnalysis
     * @return
     */
    PageMsg<TerminalAnalysis> terminalInvoiceAnalysisList(PageMsg<TerminalAnalysis> pageMsg,TerminalAnalysis terminalAnalysis,String enterpriseId);

    /**
     * 开票机发票使用情况 - 数据导出至excel(按条件导出)
     * @param request
     * @param response
     * @param terminalAnalysis
     */
    void terminalInvoiceAnalysisExport(HttpServletRequest request, HttpServletResponse response, TerminalAnalysis terminalAnalysis);

    /**
     * 月度统计 - list
     * @param pageMsg
     * @param fpzl
     * @param yearMonth
     * @return
     */
    PageMsg<MonthStatisticsData> monthStatisticsList(PageMsg<MonthStatisticsData> pageMsg, String fpzl, String yearMonth,String enterpriseId);

    /**
     * 月度统计 - total 数据
     * @param fpzl
     * @param yearMonth
     * @return
     */
    ResultMap totalMonthStatistics(String fpzl, String yearMonth,String enterpriseId);

    /**
     * 月度统计 - 数据导出至excel(按条件导出)
     * @param request
     * @param response
     */
    void monthStatisticsExport(HttpServletRequest request, HttpServletResponse response);
}
