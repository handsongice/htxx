package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
public class DlZiyoupiaoDetail {
    private Long id;

    private Long enterpriseId;

    private Long mainId;

    private String goodsName;

    private String goodsSsflbm;

    private String goodsUnit;

    private String goodsMode;

    private BigDecimal goodsAmount;

    private BigDecimal moneyIncludeTax;

    private BigDecimal taxRate;

    private Integer yhzelx;

    private String yhzenr;

   }