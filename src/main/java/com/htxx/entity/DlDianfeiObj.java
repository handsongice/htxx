package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class DlDianfeiObj implements Serializable{
    private Long id;
    private String bmbbh;
    private String userName;
    private String userTaxNo;
    private String userAddr;
    private String userBankinfo;
    private String bankInfo;
    private String addr;
    private String bz;
    private String kpr;
    private String skr;
    private String fhr;
    private String identityField;//单据号
    private String ssflbm;
    private List<DlDianfeiDetail> dlDianfeiDetail;
}