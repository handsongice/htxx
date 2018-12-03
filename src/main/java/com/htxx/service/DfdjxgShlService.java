package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.DfdjxgShl;
import com.htxx.entity.DfzypShl;
import com.htxx.entity.FpsgShl;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/12 13:03
 */
public interface DfdjxgShlService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<DfdjxgShl> getDfdjxgShlList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param dfdjxgShl
     * @return
     */
    ResultMap addDfdjxgShl(DfdjxgShl dfdjxgShl) throws Exception;

    /**
     * 更新数据
     * @param dfdjxgShl
     * @return
     */
    ResultMap updateDfdjxgShl(DfdjxgShl dfdjxgShl) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    DfdjxgShl getDfdjxgShlById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultMap delDfdjxgShl(Long id) throws Exception;
}
