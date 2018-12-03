package com.htxx.service;

import com.htxx.entity.RkLogEntity;

import java.util.List;
import java.util.Map;

public interface InvoiceStoreLogService {

    int insert(RkLogEntity record);

    List<RkLogEntity> selectByParams(Map<String, Object> map);
}