package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FpsgShLogEntity extends BaseModel {

    private Long employeeId;

    private String employeeName;

    private Long fpsgIdRef;

    private String content;

    private Integer endFlag;

    private Integer status;
}