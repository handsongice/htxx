package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Gfxx;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 8:58
 */
public interface GfxxService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Gfxx> getGfxxList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param gfxx
     * @return
     */
    ResultMap addGfxx(Gfxx gfxx) throws Exception;

    /**
     * 更新数据
     * @param gfxx
     * @return
     */
    ResultMap updateGfxx(Gfxx gfxx) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    Gfxx getGfxxById(Long id);
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delGfxx(Long id);
}
