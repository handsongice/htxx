package com.htxx.entity;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
public class DlDianfeiDetail implements Serializable{
    private Long id;

    private Long enterpriseId;

    private Long mainId;

    private String goodsName;

    private String goodsUnit;

    private String goodsMode;

    private BigDecimal goodsAmount;

    private BigDecimal moneyIncludeTax;

    private BigDecimal taxRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public String getGoodsMode() {
        return goodsMode;
    }

    public void setGoodsMode(String goodsMode) {
        this.goodsMode = goodsMode == null ? null : goodsMode.trim();
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getMoneyIncludeTax() {
        return moneyIncludeTax;
    }

    public void setMoneyIncludeTax(BigDecimal moneyIncludeTax) {
        this.moneyIncludeTax = moneyIncludeTax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}