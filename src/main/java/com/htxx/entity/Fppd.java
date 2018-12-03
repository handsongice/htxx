package com.htxx.entity;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@Alias("Fppd")
public class Fppd {

    private Long id;

    private Long enterpriseId;

    private Long kpjId;

    private String kpjName;

    private String fplx;

    private Date pdmonth;

    private String pdmonthStr;

    private Long kpAmount;

    private Long zfAmount;

    private Long chAmount;

    private Long sykc;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Integer isDel;

    public String getPdmonthStr() {
        return null;
    }

    public void setPdmonthStr(String pdmonthStr) {
        this.pdmonthStr = pdmonthStr;
    }
}