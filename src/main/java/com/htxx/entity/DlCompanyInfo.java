package com.htxx.entity;

public class DlCompanyInfo {
    private Long id;

    private String companyName;

    private String companyTaxNum;

    private String companyAddr;

    private String companyBankInfo;

    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyTaxNum() {
        return companyTaxNum;
    }

    public void setCompanyTaxNum(String companyTaxNum) {
        this.companyTaxNum = companyTaxNum == null ? null : companyTaxNum.trim();
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    public String getCompanyBankInfo() {
        return companyBankInfo;
    }

    public void setCompanyBankInfo(String companyBankInfo) {
        this.companyBankInfo = companyBankInfo == null ? null : companyBankInfo.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}