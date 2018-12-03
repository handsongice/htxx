package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@ToString
@Alias("FfEntity")
public class FfEntity {

    private Long id;

    private Integer enterpriseId;

    private String enterpriseName;

    private Integer deptId;

    private String deptName;

    private Integer kpjId;

    private String kpjName;

    private String fplx;

    private String fpdm;

    private String fphmq;

    private String fphmz;

    private Long fpsl;

    private Long ffsl;

    private Long sysl;

    private String operator;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;
}