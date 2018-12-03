package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DlGfxx {
    private Long id;

    private Long enterpriseId;

    private String gfmc;

    private String gfsh;

    private String dzdh;

    private String yhzh;

    private Byte zypWhiteList;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;


}