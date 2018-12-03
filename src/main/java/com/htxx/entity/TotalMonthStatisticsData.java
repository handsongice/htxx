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
@Slf4j
@Setter
@Getter
@ToString
@Alias("TotalMonthStatisticsData")
public class TotalMonthStatisticsData {
    /**
     * 期初库存份数
     */
    private String startStock;
    /**
     * 正数发票份数
     */
    private String plusInvoice;
    /**
     * 负数发票份数
     */
    private String minusInvoice;
    /**
     * 购进发票份数
     */
    private String purchase;
    /**
     * 期末库存份数
     */
    private String endStock;
    /**
     * 正数废票份数
     */
    private String plusCancelInvoice;
    /**
     * 负数废票份数
     */
    private String minusCancelInvoice;
    /**
     * 退回发票份数
     */
    private String returnBack;
}
