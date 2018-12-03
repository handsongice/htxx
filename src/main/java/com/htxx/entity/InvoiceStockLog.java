package com.htxx.entity;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("InvoiceStockLog")
public class InvoiceStockLog extends BaseModel {

    private String fpjxh;

    private String fplx;

    private String fpdm;

    private String fphmq;

    private String fphmz;

    private Long fpsl;

    private Long ffsl;

    private Long sysl;

    private Long stockRefId;

    private String type;

    private String status;

    private String cjsj;

    private String cjr;

    public String getCjsj() {
        return this.getCreateTime() != null ? DateUtil.formate2Times(this.getCreateTime()) : null;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return this.getCreateName();
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }
}