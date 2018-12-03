package com.htxx.service;

import com.htxx.entity.InvoiceStockLog;

import java.util.List;
import java.util.Map;

public interface InvoiceStockLogService {

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceStockLog record);

    int insertSelective(InvoiceStockLog record);

    InvoiceStockLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvoiceStockLog record);

    int updateByPrimaryKey(InvoiceStockLog record);

    List<InvoiceStockLog> selectByParams(Map<String, Object> map);
}