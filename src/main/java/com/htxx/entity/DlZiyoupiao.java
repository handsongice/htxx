package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class DlZiyoupiao {
    private Long id;

    private Long enterpriseId;

    private String identityField;

    private Byte dataType;

    private Byte djType;

    private Byte qdbz;

    private Byte fpType;

    private String monthYear;

    private String userNum;

    private String userName;

    private String userTaxNo;

    private String userAddr;

    private BigDecimal totalMoneyIncludeTax;

    private String userBankinfo;

    private String recordInfo;

    private String kpr;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;

    private String skr;

    private String fhr;

    private Long operator;


}