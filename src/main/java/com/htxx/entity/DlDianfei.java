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
public class DlDianfei implements Serializable{
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

    private String createTime;

    private String updateTime;

    private Integer isDel;
    private List<DlDianfeiDetail> dlDianfeiDetails;


}