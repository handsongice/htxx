package com.htxx.entity;

import java.math.BigDecimal;

public class Fpxe {
    private Long id;

    private Long enterpriseId;

    private Long kpjId;

    private String kpj;

    private Long fplxId;

    private String fplx;

    private Double amount;

    private String createTime;

    private String updateTime;

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

    public Long getKpjId() {
        return kpjId;
    }

    public void setKpjId(Long kpjId) {
        this.kpjId = kpjId;
    }

    public Long getFplxId() {
        return fplxId;
    }

    public void setFplxId(Long fplxId) {
        this.fplxId = fplxId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getKpj() {
        return kpj;
    }

    public void setKpj(String kpj) {
        this.kpj = kpj;
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx;
    }
}