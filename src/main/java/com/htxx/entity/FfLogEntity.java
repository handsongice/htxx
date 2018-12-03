package com.htxx.entity;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@ToString
@Alias("FfLogEntity")
public class FfLogEntity {

    private Long id;

    private Long enterpriseId;

    private String enterpriseName;

    private Long deptId;

    private String deptName;

    private Long kpjId;

    private String kpjName;

    private String fplx;

    private String fplxName;

    private String fpdm;

    private String fphmq;

    private String fphmz;

    private Long ffsl;

    private Long sysl;

    private String operator;

    private Integer status;

    private String statusStr;

    private Date createTime;

    private String createTimeStr;

    private Date updateTime;

    private Integer isDel;

    public String getCreateTimeStr() {
        return DateUtil.formate2Times(this.getCreateTime());
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}