package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Fpxe;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 14:45
 */
public interface FpxeService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Fpxe> getFpxeList(Map<String, Object> params) throws Exception;

    /**
     * 初始化数据
     * @param fpxe
     * @return
     */
    ResultMap addFpxe(Fpxe fpxe) throws Exception;

    /**
     * 更新数据
     * @param fpxe
     * @return
     */
    ResultMap updateFpxe(Fpxe fpxe);
}
