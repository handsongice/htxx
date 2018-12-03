package com.htxx.service;

import com.htxx.entity.FfLogEntity;

import java.util.List;
import java.util.Map;

public interface InvoiceDistributeLogService {

    int deleteByPrimaryKey(Long id);

    int insert(FfLogEntity record);

    int insertSelective(FfLogEntity record);

    FfLogEntity selectByPrimaryKey(Long id);

    List<FfLogEntity> selectByParams(Map<String, Object> map);

    int updateByPrimaryKeySelective(FfLogEntity record);

    int updateByPrimaryKey(FfLogEntity record);
}