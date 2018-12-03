package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.Alias;

/**
 * Created by ll on 2018-11-24
 */
@Alias("TerminalAnalysis")
@Setter
@Getter
@ToString
public class TerminalAnalysis {
    /**
     * 分机号
     */
    private String fjh;
    /**
     * 发票种类
     */
    private String fpzl;
    /**
     * 分机号 fjhNickName
     */
    private String fjhNickName;
    /**
     * 开票总数
     */
    private String kpzs;
    /**
     * 作废数
     */
    private String zfs;
    /**
     * 冲红数
     */
    private String chs;
    /*********查询参数***********/
    /**
     * 统计年月
     */
    private String yearMonth;
}
