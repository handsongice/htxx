package com.htxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;
@ToString
@Getter
@Setter
@Alias("PageMsg")
public class PageMsg<T> implements Serializable {
    /**
     * 起始页
     */
    int page;
    /**
     * 页数大小
     */
    int limit;
    /**
     * 数据数量
     */
    long count;
    /**
     * 代码
     */
    String code;
    /**
     * 信息
     */
    String msg;
    /**
     * 返回数据
     */
    List<T> data;
    /**
     * 任何类型条件
     */
    T entity;
}
