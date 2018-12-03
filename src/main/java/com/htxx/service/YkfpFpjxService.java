package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.Ykfp;
import com.htxx.pojo.FpjxParams;

import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-24
 * @description:
 */
public interface YkfpFpjxService {

    /**
     * 根据条件查询列表
     *
     * @param map
     * @return
     */
    List<Ykfp> selectListByParams(Map<String, Object> map);

    /**
     * 更新发票上缴和缴销状态
     *
     * @param fpjxParams
     * @return
     */
    BaseResultData updateFpsj(FpjxParams fpjxParams);
}
