package com.htxx.controller.statistics;

import com.htxx.constent.Constent;
import com.htxx.controller.BaseController;
import com.htxx.dto.ResultMap;
import com.htxx.entity.MonthStatisticsData;
import com.htxx.entity.PageMsg;
import com.htxx.entity.TerminalAnalysis;
import com.htxx.entity.Ykfp;
import com.htxx.mapper.YkfpMapper;
import com.htxx.service.HighPowerYkfpMngService;
import com.htxx.service.StatisticsAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ll on 2018-11-23
 * 统计分析模块统一 controller
 */
@Controller
@Slf4j
public class StatisticsAnalysisController extends BaseController {
    @Autowired
    private HighPowerYkfpMngService highPowerYkfpMngService;
    @Autowired
    private YkfpMapper ykfpMapper;
    @Autowired
    private StatisticsAnalysisService statisticsAnalysisService;

    /**
     * 发票使用分析 - 列表数据
     * @param pageMsg
     * @param ykfp
     * @return
     */
    @RequestMapping(value = "noc/statistics/invoiceAnalysis/list")
    @ResponseBody
    public PageMsg<Ykfp> invoiceAnalysisList(PageMsg<Ykfp> pageMsg, Ykfp ykfp) {
        return highPowerYkfpMngService.getList(pageMsg, ykfp);
    }

    /**
     * 开票机发票使用情况 - 列表数据
     * @param pageMsg
     * @param terminalAnalysis
     * @return
     */
    @RequestMapping(value = "noc/statistics/terminalInvoiceAnalysis/list")
    @ResponseBody
    public PageMsg<TerminalAnalysis> terminalInvoiceAnalysisList(PageMsg<TerminalAnalysis> pageMsg, TerminalAnalysis terminalAnalysis) {
        return statisticsAnalysisService.terminalInvoiceAnalysisList(pageMsg, terminalAnalysis,getLoginEnterprise().getId()+"");
    }

    /**
     * 开票机发票使用情况 - 数据导出至excel(按条件导出)
     */
    @RequestMapping(value = "noc/statistics/terminalInvoiceAnalysis/export")
    @ResponseBody
    public void terminalInvoiceAnalysisExport(HttpServletRequest request, HttpServletResponse response, TerminalAnalysis terminalAnalysis){
        statisticsAnalysisService.terminalInvoiceAnalysisExport(request,response,terminalAnalysis);
    }

    /**
     * 月度统计 - 列表数据
     * @param pageMsg
     * @return
     */
    @RequestMapping(value = "noc/statistics/monthStatistics/list")
    @ResponseBody
    public PageMsg<MonthStatisticsData> monthStatisticsList(PageMsg<MonthStatisticsData> pageMsg) {
        String yearMonth = getRequest().getParameter("yearMonth");
        String fpzl = getRequest().getParameter("fpzl");
        return statisticsAnalysisService.monthStatisticsList(pageMsg,fpzl,yearMonth,getLoginEnterprise().getId()+"");
    }

    /**
     * 月度统计 - total数据
     * @return
     */
    @RequestMapping(value = "noc/statistics/monthStatistics/total")
    @ResponseBody
    public ResultMap totalMonthStatistics(){
        String yearMonth = getRequest().getParameter("yearMonth");
        String fpzl = getRequest().getParameter("fpzl");
        return statisticsAnalysisService.totalMonthStatistics(fpzl,yearMonth,getLoginEnterprise().getId()+"");
    }

    /**
     * 月度统计 - 数据导出至excel(按条件导出)
     */
    @RequestMapping(value = "noc/statistics/monthStatistics/export")
    @ResponseBody
    public void monthStatisticsExport(HttpServletRequest request, HttpServletResponse response){
        statisticsAnalysisService.monthStatisticsExport(request,response);
    }

}
