package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FpsgShl extends BaseModel {

    private String style;

    private String type;

    private String name;

    private String code;

    private Long parentId;

    private String alt;

    private Long height;

    private Long left;

    private Long top;

    private Long width;

    private String from;

    private String marked;

    private String to;

    private String operator;

    private Long nextId;

    private Long employeeId;

    private Long lineId;
}