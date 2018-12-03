package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-28
 * @description:
 */
@Getter
@Setter
public class FppdInvParams implements Serializable {

    private String pdType;

    private String kpjCode;

    private String pdMonth;

    private String fplx;
}
