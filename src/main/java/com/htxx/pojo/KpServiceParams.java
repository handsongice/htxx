package com.htxx.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2018-11-13
 * @description: 发送开票服务器参数
 */
@Getter
@Setter
public class KpServiceParams implements Serializable {
    /**
     * 发票代码
     */
    private String typeCode;
    /**
     * 起始发票号码
     */
    private String invoiceNO;
    /**
     * 发票份数
     */
    private Long invoiceCount;
    /**
     * 发票类型
     * 0：专票
     * 2：普票
     * 11：货运
     * 12：机动车
     * 51：电子发票
     */
    private String invoiceType;
    /**
     * 终端号
     */
    private Long clientNO;
}
