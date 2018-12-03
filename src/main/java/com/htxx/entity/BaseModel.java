package com.htxx.entity;

import com.htxx.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author: zhanghs
 * @date: 2018-11-19
 * @description: 基础父model
 */
@Getter
@Setter
@ToString
public class BaseModel {
    /**
     * id
     */
    private Long id;
    /**
     * 企业id
     */
    private Long enterpriseId;
    /**
     * 企业name
     */
    private String enterpriseName;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 部门name
     */
    private String deptName;
    /**
     * 岗位id
     */
    private Long roleId;
    /**
     * 岗位name
     */
    private String roleName;
    /**
     * 开票机id
     */
    private Long kpjId;
    /**
     * 开票机终端号
     */
    private String kpjCode;
    /**
     * 开票机name
     */
    private String kpjName;
    /**
     * 创建人id
     */
    private Long createId;
    /**
     * 创建人name
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDel;

    private String createTimeStr;
    private String updateTimeStr;

    public String getCreateTimeStr() {
        return this.createTime != null ? DateUtil.formate2Times(this.createTime) : null;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return this.updateTime != null ? DateUtil.formate2Times(this.updateTime) : null;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}
