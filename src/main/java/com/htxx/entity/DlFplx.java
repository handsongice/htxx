package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DlFplx {

    private Long id;

    private Long enterpriseId;

    private String name;

    private String code;

    private Date createTime;

    private Date updateTime;

    private Integer isDel;
}