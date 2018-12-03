package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Administrator
 * @date: 2018-11-28
 * @description:
 */
@Getter
@Setter
public class FpcxParams implements Serializable {

    private Long enterpriseId;

    private String kpjCode;

    private List<String> kpjCodeList;

    private String fpzl;

    private String kplx;

    private String fpxz;

    private String slvStr;

    private String notNormalSlv;

    private String gfmc;

    private String kpsjMin;

    private String kpsjMax;

    private String fpdm;

    private String fphmq;

    private String fphmz;
}
