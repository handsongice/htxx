package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * Created by ll on 2018-11-01
 * 已开发票 明细信息 detail
 */
@Setter
@Getter
@ToString
@Alias("YkfpDel")
public class YkfpDel {
    /**
     * 已开发票 明细 id
     */
    private int id;
    /**
     * 单据号
     */
    private String djh;
    /**
     * 序号
     */
    private int xh;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 税收分类编码（商品编码）
     */
    private String shflbm;
    /**
     * 企业自编码
     */
    private String qyzbm;
    /**
     * 规格型号
     */
    private String ggxh;
    /**
     * 计价单位
     */
    private String jldw;
    /**
     * 数量
     */
    private Double xmsl;
    /**
     * 单价 不含税
     */
    private Double xmdj;
    /**
     * 金额 不含税
     */
    private Double xmje;
    /**
     * 税率
     */
    private Double sl;
    /**
     * 税率 百分比
     */
    private String slStr;
    /**
     * 税额
     */
    private Double se;
    /**
     * 含税单价
     */
    private Double hsdj;
    /**
     * 含税金额
     */
    private Double hsje;
    /**
     * 优惠政策标识
     */
    private int yhzcbs;
    /**
     * 优惠政策内容
     */
    private String yhzcnr;
    /**
     * 零税率标识
     */
    private int lslbs;
    /**
     * 发票行性质  是否为折扣行
     */
    private int fphxz;
    /**
     * 业务类型
     */
    private int ywlx;
    /**
     * 含税标志
     */
    private int hsbz;
    /**
     * 已开发票主表
     */
    private Ykfp ykfp;

    /*************导出excel************/
    private String fplxName;

    private String fpdm;

    private String fphm;

    private String kpjName;

    private String gfsh;

    private String gfmc;

    private String kpsj;

    private String kpr;

    private String bz;

    private String ssyf;
}
