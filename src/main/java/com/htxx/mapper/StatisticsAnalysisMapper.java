package com.htxx.mapper;

import com.htxx.entity.MonthStatisticsData;
import com.htxx.entity.TerminalAnalysis;
import com.htxx.entity.TotalMonthStatisticsData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by ll on 2018-11-24
 * 统计分析模块 mapper
 */
@Component
public interface StatisticsAnalysisMapper {
    /**
     * 获取开票机发票使用情况 - 列表数据
     * @param map
     * @return
     */
    List<TerminalAnalysis> getTerminalAnalysisList(Map<String,Object> map);

    /**
     * 获取月度统计 - 列表数据
     * @param map
     * @return
     */
    List<MonthStatisticsData> monthStatisticsResult(Map<String,Object> map);

    /**
     * 获取月度统计 - total数据
     * @param map
     * @return
     */
    TotalMonthStatisticsData totalMonthStatisticsResult(Map<String,Object> map);

}
