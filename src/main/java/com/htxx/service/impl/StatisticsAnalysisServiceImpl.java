package com.htxx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.*;
import com.htxx.mapper.StatisticsAnalysisMapper;
import com.htxx.pojo.InvoiceStockCountParams;
import com.htxx.service.InvoiceStockService;
import com.htxx.service.StatisticsAnalysisService;
import com.htxx.util.PoiExcelExportUtil;
import com.htxx.util.PoiExcelExportUtilForStatistics;
import com.htxx.util.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.htxx.constent.Constent.SESSION_ENTERPRISE;

/**
 * Created by ll on 2018-11-24
 * 统计分析模块 service
 */
@Service
@Slf4j
public class StatisticsAnalysisServiceImpl implements StatisticsAnalysisService {
    @Autowired
    private StatisticsAnalysisMapper statisticsAnalysisMapper;
    @Autowired
    private InvoiceStockService invoiceStockService;
    @Override
    public PageMsg<TerminalAnalysis> terminalInvoiceAnalysisList(PageMsg<TerminalAnalysis> pageMsg, TerminalAnalysis terminalAnalysis,String enterpriseId) {
        try {
            log.info("pageMsg:" + pageMsg);
            log.info("terminalAnalysis:" + terminalAnalysis);
            //传入起始页与页数大小
//            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
//            pageMsg.setEntity(terminalAnalysis);
            Map<String, Object> map = new HashMap<>();
            map.put("v_time",terminalAnalysis.getYearMonth());
            map.put("v_fjh",terminalAnalysis.getFjh());
            map.put("v_fpzl",terminalAnalysis.getFpzl());
            map.put("v_enterpriseId",enterpriseId);
            log.info("map:"+map);
            //带条件查询
            List<TerminalAnalysis> all = statisticsAnalysisMapper.getTerminalAnalysisList(map);
            //将用户数据封装到PageInfo 中
//            PageInfo<TerminalAnalysis> page = new PageInfo(all);
            //设置成功代码
            pageMsg.setCode("0");
            //设置查询数据
            pageMsg.setData(all);
            //设置数据数量 return result;
            pageMsg.setCount(all.size());
            return pageMsg;
        } catch (Exception e) {
            log.error("StatisticsAnalysisServiceImpl.getTerminalInvoiceAnalysisList捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public void terminalInvoiceAnalysisExport(HttpServletRequest request, HttpServletResponse response, TerminalAnalysis terminalAnalysis) {
        try {
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String now = df.format(new Date());
            //创建文件名称
            String fileName = "开票机发票使用情况数据统计" + now + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, request, response);
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"开票机", "统计年月", "发票类型", "开票总数（张）", "作废数（张）", "红字数（张）"};
            String[] cols = {"fjhNickName", "yearMonth", "fpzl", "kpzs", "zfs", "chs"};
            // 统计列表
            List<TerminalAnalysis> list;
            Map<String, Object> map = new HashMap<>();
            Enterprise enterprise = (Enterprise) request.getSession().getAttribute(SESSION_ENTERPRISE);
            map.put("v_time",terminalAnalysis.getYearMonth());
            map.put("v_fjh",terminalAnalysis.getFjh());
            map.put("v_fpzl",terminalAnalysis.getFpzl());
            map.put("v_enterpriseId",enterprise.getId()+"");
            log.info("map:"+map);
            //获取已开发票所有列表
            list = statisticsAnalysisMapper.getTerminalAnalysisList(map);

            // 导出时 分机号，发票类型,发票状态 字段 数字标识变文字描述
            list.forEach(item -> {
                //发票类型
                if (Constent.SIGN_STRING_0.equals(item.getFpzl())) {
                    item.setFpzl("专票");
                } else if (Constent.SIGN_STRING_2.equals(item.getFpzl())) {
                    item.setFpzl("普票");
                } else if (Constent.SIGN_STRING_41.equals(item.getFpzl())) {
                    item.setFpzl("卷票");
                } else if (Constent.SIGN_STRING_51.equals(item.getFpzl())) {
                    item.setFpzl("电子票");
                }
            });
            log.info("list:" + list);
            //构造excel导出类
            PoiExcelExportUtil<TerminalAnalysis> pee = new PoiExcelExportUtil(fileName, heads, cols, list, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("StatisticsAnalysisServiceImpl.terminalInvoiceAnalysisExport捕获异常！");
        }
    }

    @Override
    public PageMsg<MonthStatisticsData> monthStatisticsList(PageMsg<MonthStatisticsData> pageMsg, String fpzl, String yearMonth,String enterpriseId) {
        try {
            log.info("pageMsg:" + pageMsg);
            log.info("fpzl:" + fpzl);
            log.info("yearMonth:" + yearMonth);
            log.info("enterpriseId:" + enterpriseId);
            //传入起始页与页数大小
//            PageHelper.startPage(pageMsg.getPage(), pageMsg.getLimit());
            //设置条件
//            pageMsg.setEntity(terminalAnalysis);
            Map<String, Object> map = new HashMap<>();
            map.put("v_time",yearMonth);
            map.put("v_fpzl",fpzl);
            map.put("v_enterpriseId",enterpriseId);
            log.info("map:"+map);
            //带条件查询
            List<MonthStatisticsData> all = statisticsAnalysisMapper.monthStatisticsResult(map);
            //将用户数据封装到PageInfo 中
//            PageInfo<TerminalAnalysis> page = new PageInfo(all);
            //设置成功代码
            pageMsg.setCode("0");
            //设置查询数据
            pageMsg.setData(all);
            //设置数据数量 return result;
            pageMsg.setCount(all.size());
            return pageMsg;
        } catch (Exception e) {
            log.error("StatisticsAnalysisServiceImpl.monthStatisticsList捕获异常！" + e);
            return pageMsg;
        }
    }

    @Override
    public ResultMap totalMonthStatistics(String fpzl, String yearMonth,String enterpriseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("v_time",yearMonth);
        map.put("v_fpzl",fpzl);
        map.put("v_enterpriseId",enterpriseId);
        log.info("map:"+map);
        TotalMonthStatisticsData totalMonthStatisticsData= statisticsAnalysisMapper.totalMonthStatisticsResult(map);
        InvoiceStockCountParams invoiceStockCountParams = invoiceStockService.selectStockCount(yearMonth+"-01 00:00:00",yearMonth+"-31 23:59:59",fpzl);
        log.info("invoiceStockCountParams="+invoiceStockCountParams);
        totalMonthStatisticsData.setPurchase(invoiceStockCountParams.getBuyCount()+"");
        totalMonthStatisticsData.setReturnBack(invoiceStockCountParams.getReturnCount()+"");
        totalMonthStatisticsData.setStartStock(invoiceStockCountParams.getBeginMonthCount()+"");
        totalMonthStatisticsData.setEndStock(invoiceStockCountParams.getEndMonthCount()+"");
        log.info("totalMonthStatisticsData="+totalMonthStatisticsData);
        return ResultMap.success(totalMonthStatisticsData);
    }

    @Override
    public void monthStatisticsExport(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String now = df.format(new Date());
            //创建文件名称
            String fileName = "月度数据统计" + now + ".xls";
            //Servlet准备 设置兼容浏览器 的OutputStream流
            ServletUtil su = new ServletUtil(fileName, request, response);
            su.poiExcelServlet();
            //设置excel 标题，列的取值field
            String[] heads = {"项目名称", "合计", "3%", "10%", "16%", "其他"};
            String[] cols = {"xmmc", "total", "three", "ten", "sixteen", "other"};
            String yearMonth = request.getParameter("yearMonth");
            String fpzl = request.getParameter("fpzl");
            Map<String, Object> map = new HashMap<>();
            Enterprise enterprise = (Enterprise)request.getSession().getAttribute(SESSION_ENTERPRISE);
            map.put("v_time",yearMonth);
            map.put("v_enterpriseId",enterprise.getId()+"");
            map.put("v_fpzl",fpzl);
            log.info("map:"+map);
            String fpzlStr = "";
            switch (fpzl){
                case "0":
                    fpzlStr = "专用发票";
                    break;
                case "2":
                    fpzlStr = "普通发票";
                    break;
                case "51":
                    fpzlStr = "电子发票";
                    break;
                case "41":
                    fpzlStr = "普通发票（卷）";
                    break;
                default:break;
            }
            TotalMonthStatisticsData totalMonthStatisticsData= statisticsAnalysisMapper.totalMonthStatisticsResult(map);
            InvoiceStockCountParams invoiceStockCountParams = invoiceStockService.selectStockCount(yearMonth+"-01 00:00:00",yearMonth+"-31 23:59:59",fpzl);
            String[] heads_total = {"统计年月", "发票类型","期初库存份数", "期末库存份数", "正数发票份数", "正数废票份数", "负数发票份数", "负数废票份数", "购进发票份数", "退回发票份数"};
            String[] cols_total = {yearMonth, fpzlStr,invoiceStockCountParams.getBeginMonthCount(), invoiceStockCountParams.getEndMonthCount(), totalMonthStatisticsData.getPlusInvoice(), totalMonthStatisticsData.getPlusCancelInvoice(), totalMonthStatisticsData.getMinusInvoice(), totalMonthStatisticsData.getMinusCancelInvoice(), invoiceStockCountParams.getBuyCount(), invoiceStockCountParams.getReturnCount()};
            // 统计列表
            List<MonthStatisticsData> list;
            //获取已开发票所有列表
            list = statisticsAnalysisMapper.monthStatisticsResult(map);
            log.info("list:" + list);
            //构造excel导出类
            PoiExcelExportUtilForStatistics<MonthStatisticsData> pee = new PoiExcelExportUtilForStatistics(fileName, heads, cols, heads_total,cols_total,list, su.getOut());
            //excel导出 至浏览器
            pee.exportExcel();
        } catch (Exception e) {
            log.error("StatisticsAnalysisServiceImpl.monthStatisticsExport捕获异常！");
        }
    }
}
