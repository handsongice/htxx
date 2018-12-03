package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-26
 * @description:
 */
@Getter
@Setter
public class FpjxParams implements Serializable {

    private Long fpId;

    private String fpIds;

    private String type;

    private Long enterpriseId;

    private Long createId;

    private String createName;
}
