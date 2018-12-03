package com.htxx.entity;

public class DlSpxx {
    private Long id;

    private Byte ywlx;

    private String spmc;

    private String shflbm;

    private String sl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getYwlx() {
        return ywlx;
    }

    public void setYwlx(Byte ywlx) {
        this.ywlx = ywlx;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc == null ? null : spmc.trim();
    }

    public String getShflbm() {
        return shflbm;
    }

    public void setShflbm(String shflbm) {
        this.shflbm = shflbm == null ? null : shflbm.trim();
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl == null ? null : sl.trim();
    }
}