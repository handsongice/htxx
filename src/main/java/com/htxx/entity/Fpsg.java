package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("Fpsg")
public class Fpsg extends BaseModel {

    private Long employeeId;

    private String employeeName;

    private Long shIdRef;

    private Long shlIdNext;

    private String shNameNext;

    private Integer shStatusNow;

    private String fplx;

    private String fpdm;

    private Integer slsl;

    private Integer lpfs;

    private String jby;

    private String operator;

    private String status;

    private String mark;
    /**
     * 冗余字段，用于导出excel
     */
    private String fplxStr;

    private String lpfsStr;
}