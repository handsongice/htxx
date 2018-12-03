package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-12
 * @description:
 */
@Getter
@Setter
public class DistributeParams implements Serializable {

    private Long rkId;

    private Long kpjId;

    private String kpjName;

    private String fphmq;

    private Long ffsl;

    private Long createId;

    private String createName;
}
