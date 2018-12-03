package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ll
 * 高可靠 通知单 实体
 */
@Setter
@Getter
@ToString
@Alias("Notice")
public class Notice implements Serializable {
    /**
     * 高可靠通知单id
     */
    private int id;
    /**
     * 户名
     */
    private String hm;
    /**
     * 账号
     */
    private String zh;
    /**
     * 工作单号
     */
    private String gzdh;
    /**
     * 金额
     */
    private Double je;
    /**
     * 缴费标准
     */
    private String jfbz;
    /**
     * 状态 1-未提交  2-已提交
     */
    private String status;
    /**
     * 创建时间
     */
    private String createTime;
    /*****************************************************/
    /**
     * 分机号 主机默认为0
     */
    private String fjh;
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
     * 业务类型 1-普通电费 2-高可靠电费 3-自由票
     */
    private String ywlx;
    /**
     * 操作员ID
     */
    private Long employeeId;
    /**
     * 企业ID
     */
    private Long enterpriseId;
    /**
     * 通知单明细列表
     */
    private List<YkfpDel> noticeDelList;
    /*****************************************************/
    /**
     * 搜索条件 -- 金额最小值
     */
    private Double jeMin;
    /**
     * 搜索条件 -- 金额最大值
     */
    private Double jeMax;
    /**
     * 搜索条件 -- 创建时间最大值
     */
    private String createTimeMin;
    /**
     * 搜索条件 -- 创建时间最大值
     */
    private String createTimeMax;


}
