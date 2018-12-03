package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Kpjxx {

    private Long id;

    private Long enterpriseId;

    private Long deptId;

    private String deptName;

    private String name;

    private String nickName;

    private String code;

    private String createTime;

    private String updateTime;

    private Integer isDel;

    private boolean checked = false;
}