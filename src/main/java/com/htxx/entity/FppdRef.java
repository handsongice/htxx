package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Getter
@Setter
@Alias("FppdRef")
public class FppdRef {

    private Long id;

    private Long enterpriseId;

    private String fplx;

    private String kpjCode;

    private String pdMonth;

    private Long createId;

    private String createName;

    private Date createTime;

}