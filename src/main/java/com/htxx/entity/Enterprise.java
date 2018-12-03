package com.htxx.entity;

public class Enterprise {
    private Long id;

    private String name;

    private String taxNum;

    private String addr;

    private String bankInfo;

    private Integer etype;

    private Integer num;

    private String createTime;

    private String updateTime;

    private Integer isDel;

    private String loginCode;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum == null ? null : taxNum.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo == null ? null : bankInfo.trim();
    }

    public Integer getEtype() {
        return etype;
    }

    public void setEtype(Integer etype) {
        this.etype = etype;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}