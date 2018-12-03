package com.htxx.entity;

import java.util.Date;

public class Gfxx {
    private Long id;

    private Long enterpriseId;

    private String gfmc;

    private String gfsh;

    private String dzdh;

    private String yhzh;

    private Byte zypWhiteList;

    private String createTime;

    private String updateTime;

    private Integer isDel;

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

    public String getGfmc() {
        return gfmc;
    }

    public void setGfmc(String gfmc) {
        this.gfmc = gfmc == null ? null : gfmc.trim();
    }

    public String getGfsh() {
        return gfsh;
    }

    public void setGfsh(String gfsh) {
        this.gfsh = gfsh == null ? null : gfsh.trim();
    }

    public String getDzdh() {
        return dzdh;
    }

    public void setDzdh(String dzdh) {
        this.dzdh = dzdh == null ? null : dzdh.trim();
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh == null ? null : yhzh.trim();
    }

    public Byte getZypWhiteList() {
        return zypWhiteList;
    }

    public void setZypWhiteList(Byte zypWhiteList) {
        this.zypWhiteList = zypWhiteList;
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Gfxx{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", gfmc='" + gfmc + '\'' +
                ", gfsh='" + gfsh + '\'' +
                ", dzdh='" + dzdh + '\'' +
                ", yhzh='" + yhzh + '\'' +
                ", zypWhiteList=" + zypWhiteList +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}