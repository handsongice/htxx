package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class DlDianfeiForExcel {
    private Long id;

    private Long enterpriseId;

    private String identityField;

    private String dataType;

    private String djType;

    private String monthYear;

    private String userNum;

    private String exportNnum;

    private String userName;

    private String userTaxNo;

    private String userAddr;

    private BigDecimal totalMoneyIncludeTax;

    private String userBankinfo;

    private String recordInfo;

    private Long operator;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;


    private String goodsName;

    private String goodsUnit;

    private String goodsMode;

    private BigDecimal goodsAmount;

    private BigDecimal moneyIncludeTax;

    private BigDecimal taxRate;
}