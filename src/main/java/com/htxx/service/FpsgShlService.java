package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.FpsgShl;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/12 13:03
 */
public interface FpsgShlService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<FpsgShl> getFpsgShlList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param fpsgShl
     * @return
     */
    ResultMap addFpsgShl(FpsgShl fpsgShl) throws Exception;

    /**
     * 更新数据
     * @param fpsgShl
     * @return
     */
    ResultMap updateFpsgShl(FpsgShl fpsgShl) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    FpsgShl getFpsgShlById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultMap delFpsgShl(Long id) throws Exception;
}
