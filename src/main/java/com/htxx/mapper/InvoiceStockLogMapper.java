package com.htxx.mapper;

import com.htxx.entity.InvoiceStockLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InvoiceStockLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(InvoiceStockLog record);

    int insertSelective(InvoiceStockLog record);

    InvoiceStockLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvoiceStockLog record);

    int updateByPrimaryKey(InvoiceStockLog record);

    List<InvoiceStockLog> selectByParams(Map<String, Object> map);
}