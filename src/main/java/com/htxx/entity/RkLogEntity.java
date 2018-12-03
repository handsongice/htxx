package com.htxx.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@ToString
@Alias("RkLogEntity")
public class RkLogEntity {

    private Long id;

    private Long enterpriseId;

    private String enterpriseName;

    private Long deptId;

    private String deptName;

    private Long rkRefId;

    private String fplx;

    private String fpdm;

    private String fphmq;

    private Long rksl;

    private String operator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createTimeStr;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer isDel;

    public String getCreateTimeStr() {
        return DateUtil.formate2Times(this.createTime);
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}