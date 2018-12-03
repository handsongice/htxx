package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: SaHongzhi
 * @Date: 2018-11-20 14:02
 */

@Getter
@Setter
@ToString
public class QryInvResult {
    private String retCode;
    private String retMsg;
    private String infoKind;
    private String infoTypeCode;
    private String infoNumber;
    private String infoBillNumber;
    private double infoAmount;
    private double infoTaxAmount;
    private String infoInvDate;
    private String printFlag;
    private String uploadFlag;
    private String cancelFlag;
    private String info;
    private String fpzl;
    private String kplx;
    private String ywlx;
    private String machineNo;
    private String yfpdm;
    private String yfphm;
    private String chyy;
}
