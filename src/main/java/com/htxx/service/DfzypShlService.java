package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.DfzypShl;
import com.htxx.entity.FpsgShl;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/12 13:03
 */
public interface DfzypShlService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<DfzypShl> getDfzypShlList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param dfzypShl
     * @return
     */
    ResultMap addDfzypShl(DfzypShl dfzypShl) throws Exception;

    /**
     * 更新数据
     * @param dfzypShl
     * @return
     */
    ResultMap updateDfzypShl(DfzypShl dfzypShl) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    DfzypShl getDfzypShlById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultMap delDfzypShl(Long id) throws Exception;
}
