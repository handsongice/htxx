package com.htxx.entity;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author: zhanghs
 * @date: 2018-11-19
 * @description: 发票库存
 */
@Getter
@Setter
@ToString
@Alias("InvoiceStock")
public class InvoiceStock extends BaseModel {

    private String fpjxh;

    private String fplx;

    private String fplxName;

    private String fpdm;

    private String fphmq;

    private String fphmz;

    private Long fpsl;

    private Long ffsl;

    private Long sysl;

    private String kpjNo;

    private Date gmrq;

    private Double kpxe;

    private String gmrqStr;

    private String operator;

    private Integer qrbz;

    private Integer status;

    private Long fromId;

    public String getGmrqStr() {
        return this.gmrq == null ? null : DateUtil.formate2Times(this.gmrq);
    }

    public void setGmrqStr(String gmrqStr) {
        this.gmrqStr = gmrqStr;
    }
}
