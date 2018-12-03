package com.htxx.service.impl;

import com.htxx.entity.RkLogEntity;
import com.htxx.mapper.RkLogEntityMapper;
import com.htxx.service.InvoiceStoreLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: hszhang
 * @date: 2018-11-07
 * @description: 入库记录ServiceImpl
 */
@Service
@Transactional
public class InvoiceStoreLogServiceImpl implements InvoiceStoreLogService {

    @Autowired
    private RkLogEntityMapper rkLogEntityMapper;

    @Override
    public int insert(RkLogEntity record) {
        return rkLogEntityMapper.insert(record);
    }

    @Override
    public List<RkLogEntity> selectByParams(Map<String, Object> map) {
        return rkLogEntityMapper.selectByParams(map);
    }
}
