package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Fplx;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 12:59
 */
public interface FplxService {
    /**
     * 列表
     *
     * @param params
     * @return
     * @throws Exception
     */
    List<Fplx> getFplxList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     *
     * @param fplx
     * @return
     */
    ResultMap addFplx(Fplx fplx) throws Exception;

    /**
     * 更新数据
     *
     * @param fplx
     * @return
     */
    ResultMap updateFplx(Fplx fplx) throws Exception;

    /**
     * 获取信息
     *
     * @param id
     * @return
     */
    Fplx getFplxById(Long id);

    /**
     * 逻辑删除
     *
     * @param id
     * @return
     */
    ResultMap delFplx(Long id);

    List<Fplx> selectByParams(Fplx fplx);
}
