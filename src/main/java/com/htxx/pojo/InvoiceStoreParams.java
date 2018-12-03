package com.htxx.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@ToString
public class InvoiceStoreParams {

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

    private String operator;
}