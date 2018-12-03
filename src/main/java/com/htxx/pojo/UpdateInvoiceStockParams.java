package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: Administrator
 * @date: 2018-11-22
 * @description:
 */
@Getter
@Setter
public class UpdateInvoiceStockParams {

    private Long enterpriseId;

    private String fpdm;

    private String fphm;

    private String kpjCode;

    private String type;

    private Long createId;

    private String createName;
}
