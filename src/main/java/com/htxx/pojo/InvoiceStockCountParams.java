package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-26
 * @description:
 */
@Getter
@Setter
@ToString
public class InvoiceStockCountParams implements Serializable {
    /**
     * 购进数量
     */
    private String buyCount;
    /**
     * 退回数量
     */
    private String returnCount;
    /**
     * 月初数量
     */
    private String beginMonthCount;
    /**
     * 月末数量
     */
    private String endMonthCount;

    private String fplx;

    private String beginDateStr;

    private String endDateStr;

    public String getBuyCount() {
        return buyCount == null ? "0" : buyCount;
    }

    public String getReturnCount() {
        return returnCount == null ? "0" : returnCount;
    }

    public String getBeginMonthCount() {
        return beginMonthCount == null ? "0" : beginMonthCount;
    }

    public String getEndMonthCount() {
        return endMonthCount == null ? "0" : endMonthCount;
    }
}
