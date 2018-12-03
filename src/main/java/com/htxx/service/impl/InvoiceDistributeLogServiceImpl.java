package com.htxx.service.impl;

import com.htxx.entity.FfLogEntity;
import com.htxx.mapper.FfLogEntityMapper;
import com.htxx.service.InvoiceDistributeLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-12
 * @description:
 */
@Transactional
@Service
@Slf4j
public class InvoiceDistributeLogServiceImpl implements InvoiceDistributeLogService {

    @Autowired
    private FfLogEntityMapper ffLogEntityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return ffLogEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(FfLogEntity record) {
        return ffLogEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(FfLogEntity record) {
        return ffLogEntityMapper.insertSelective(record);
    }

    @Override
    public FfLogEntity selectByPrimaryKey(Long id) {
        return ffLogEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FfLogEntity> selectByParams(Map<String, Object> map) {
        return ffLogEntityMapper.selecyByParams(map);
    }

    @Override
    public int updateByPrimaryKeySelective(FfLogEntity record) {
        return ffLogEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(FfLogEntity record) {
        return ffLogEntityMapper.updateByPrimaryKey(record);
    }
}
