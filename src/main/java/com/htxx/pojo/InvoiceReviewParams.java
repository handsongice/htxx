package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-23
 * @description:
 */
@Getter
@Setter
public class InvoiceReviewParams implements Serializable {

    private Long id;

    private String content;

    private String type;
}
