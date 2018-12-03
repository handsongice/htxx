package com.htxx.service;

import com.htxx.common.BaseResultData;
import com.htxx.entity.Fpsg;

import java.util.List;
import java.util.Map;

public interface FpsgService {

    int deleteByPrimaryKey(Fpsg key);

    int insert(Fpsg record);

    int insertSelective(Fpsg record);

    Fpsg selectByPrimaryKey(Fpsg key);

    int updateByPrimaryKeySelective(Fpsg record);

    int updateByPrimaryKey(Fpsg record);

    List<Fpsg> selectByParams(Map<String, Object> map);

    /**
     * 审核通过
     *
     * @return
     */
    BaseResultData updateReviewPass();

    /**
     * 审核拒绝
     *
     * @return
     */
    BaseResultData updateReviewReject();
}
