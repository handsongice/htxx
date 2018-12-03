package com.htxx.entity;

import java.util.Date;

public class DlFpxe {
    private Long id;

    private Long enterpriseId;

    private Long kpjId;

    private String kpj;

    private Long fplxId;

    private String fplx;

    private Double amount;

    private Date createTime;

    private Date updateTime;

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

    public String getKpj() {
        return kpj;
    }

    public void setKpj(String kpj) {
        this.kpj = kpj == null ? null : kpj.trim();
    }

    public Long getFplxId() {
        return fplxId;
    }

    public void setFplxId(Long fplxId) {
        this.fplxId = fplxId;
    }

    public String getFplx() {
        return fplx;
    }

    public void setFplx(String fplx) {
        this.fplx = fplx == null ? null : fplx.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}