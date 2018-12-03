package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

/**
 * Created by ll on 2018-11-26
 * 月度统计
 */
@Setter
@Getter
@ToString
@Alias("MonthStatisticsData")
public class MonthStatisticsData {
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 合计
     */
    private String total;
    /**
     * 3%
     */
    private String three;
    /**
     * 10%
     */
    private String ten;
    /**
     * 16%
     */
    private String sixteen;
    /**
     * 其他
     */
    private String other;
}
