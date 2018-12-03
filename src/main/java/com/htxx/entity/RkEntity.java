package com.htxx.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@ToString
@Alias("RkEntity")
public class RkEntity {

    private Long id;

    private Long enterpriseId;

    private String enterpriseName;

    private Long deptId;

    private String deptName;

    private Long kpjId;

    private String kpjName;

    private String fplx;

    private String fpdm;

    private String fphmq;

    private String fphmz;

    private Long rksl;

    private Long ffsl;

    private Long sysl;

    private String operator;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer isDel;
}