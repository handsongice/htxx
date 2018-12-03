package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-05 9:46
 */
@ToString
@Setter
@Getter
public class InvoiceUploadResult {
    private Integer errorNum;
    private Integer normalNum;
    private Integer totalNum;
    private String yearMonth;
    private Date importDate;
    private String operator;



}
