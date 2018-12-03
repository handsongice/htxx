package com.htxx.service.impl;

import com.htxx.entity.InvoiceStockLog;
import com.htxx.mapper.InvoiceStockLogMapper;
import com.htxx.service.InvoiceStockLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-20
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceStockLogServiceImpl implements InvoiceStockLogService {

    @Autowired
    private InvoiceStockLogMapper invoiceStockLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return invoiceStockLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(InvoiceStockLog record) {
        return invoiceStockLogMapper.insert(record);
    }

    @Override
    public int insertSelective(InvoiceStockLog record) {
        return invoiceStockLogMapper.insertSelective(record);
    }

    @Override
    public InvoiceStockLog selectByPrimaryKey(Long id) {
        return invoiceStockLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(InvoiceStockLog record) {
        return invoiceStockLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InvoiceStockLog record) {
        return invoiceStockLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<InvoiceStockLog> selectByParams(Map<String, Object> map) {
        return invoiceStockLogMapper.selectByParams(map);
    }
}
