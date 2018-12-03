package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-19 9:11
 */

@Getter
@Setter
@ToString
public class KpResult {
    private String infoAmount;
    private String infoTaxAmount;
    private String infMonth;
    private String typeCode;
    private String Number;
    private String goodsListFlag;
    private String retCode;
    private String machineNo;
    private String retMsg;
}
