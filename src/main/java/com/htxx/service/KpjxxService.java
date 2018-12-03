package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Kpjxx;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/5 13:47
 */
public interface KpjxxService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Kpjxx> getKpjxxList(Map<String, Object> params) throws Exception;

    /**
     * 新建
     * @param kpjxx
     * @return
     * @throws Exception
     */
    ResultMap addKpjxx(Kpjxx kpjxx) throws Exception;

    /**
     * 更新
     * @param kpjxx
     * @return
     */
    ResultMap updateKpjxx(Kpjxx kpjxx);

    /**
     * 详情
     * @param id
     * @return
     */
    Kpjxx getKpjxxById(Long id);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultMap delKpjxx(Long id);

    /**
     * 查询开票机对应的部门信息
     *
     * @param map
     * @return
     */
    List<Kpjxx> selectKpjDeptByParams(Map<String, Object> map);
}
