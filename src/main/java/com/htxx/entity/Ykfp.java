package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * Created by ll on 2018-11-01
 * 已开发票 全票面信息 main + detail
 */
@Setter
@Getter
@ToString
@Alias("Ykfp")
public class Ykfp {
    /**
     * 已开发票id
     */
    private Long id;
    /**
     * 单据号
     */
    private String djh;
    /**
     * 分机号 主机默认为0
     */
    private String fjh;
    /**
     * 分机号 nikeName
     */
    private String nickName;
    /**
     * 开票终端号
     */
    private String kpzdh;
    /**
     * 发票种类41:卷票 51:电子票 0：专票 2：普票
     */
    private String fpzl;
    /**
     * 开票类型1-蓝字发票；2-红字发票；3-废票
     */
    private String kplx;
    /**
     * 销方税号
     */
    private String xfsh;
    /**
     * 销方名称
     */
    private String xfmc;
    /**
     * 销方地址电话
     */
    private String xfdzdh;
    /**
     * 销方银行账号
     */
    private String xfyhzh;
    /**
     * 购方税号
     */
    private String gfsh;
    /**
     * 购方名称
     */
    private String gfmc;
    /**
     * 购方地址电话
     */
    private String gfdzdh;
    /**
     * 购方银行账号
     */
    private String gfyhzh;
    /**
     * 开票人
     */
    private String kpr;
    /**
     * 收款人
     */
    private String skr;
    /**
     * 复核人
     */
    private String fhr;
    /**
     * 发票代码
     */
    private String fpdm;
    /**
     * 发票号码
     */
    private String fphm;
    /**
     * 原发票代码
     */
    private String yfpdm;
    /**
     * 原发票号码
     */
    private String yfphm;
    /**
     * 清单标志 0-非清单  1-清单
     */
    private int qdbz;
    /**
     * 价税合计
     */
    private Double jshj;
    /**
     * 合计金额 不含税，单位：元（ 2 位小
     */
    private Double hjje;
    /**
     * 合计税额
     */
    private Double hjse;
    /**
     * 税收分类编码版本号
     */
    private String bmbbh;
    /**
     * 备注
     */
    private String bz;
    /**
     * 手机号
     */
    private String sj;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 开票时间
     */
    private String kpsj;
    /**
     * 冲红标志 0-正常 1-已冲红
     */
    private int chbz;
    /**
     * 作废标志 0-正常 1-已作废
     */
    private int zfbz;
    /**
     * 业务类型 1-普通电费 2-高可靠电费 3-自由票
     */
    private String ywlx;
    /**
     * 企业Id
     */
    private Long enterpriseId;
    /**
     * 操作员Id
     */
    private Long employeeId;
    /**
     * 冲红原因
     */
    private String chyy;
    /**
     * 发票明细列表
     */
    private List<YkfpDel> ykfpDelList;
    /**
     * 已开发票和发票缴销关联
     */
    private YkfpFpjx ykfpFpjx;

    /**********导出Excel用***********/
    /**
     * 上缴缴销标志
     */
    private String sjjxbzStr;

    /*****************************************************/
    /**
     * 查询条件 -- 开票时间最小值
     */
    private String kpsjMin;
    /**
     * 查询条件 -- 开票时间最大值
     */
    private String kpsjMax;
    /**
     * 查询条件 -- 价税合计最小值
     */
    private Double jshjMin;
    /**
     * 查询条件 -- 价税合计最大值
     */
    private Double jshjMax;
    /**
     * 查询条件 -- 发票号码min
     */
    private String fphmMin;
    /**
     * 查询条件 -- 发票号码max
     */
    private String fphmMax;
    /**
     * 查询条件 -- 开票时间（年-月）
     */
    private String kpsjYearMonth;
    /**
     * 查询条件 -- 税率（数组）
     */
    private String slvStr;
    /**
     * 查询条件 -- 发票性质
     */
    private String fpxz;
}
