package com.htxx.service.impl;

import com.htxx.entity.Fppd;
import com.htxx.mapper.FppdMapper;
import com.htxx.service.FppdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: Administrator
 * @date: 2018-11-08
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FppdServiceImpl implements FppdService {

    @Autowired
    private FppdMapper fppdMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return fppdMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Fppd record) {
        return fppdMapper.insert(record);
    }

    @Override
    public int insertSelective(Fppd record) {
        return fppdMapper.insertSelective(record);
    }

    @Override
    public Fppd selectByPrimaryKey(Long id) {
        return fppdMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Fppd record) {
        return fppdMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Fppd record) {
        return fppdMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Fppd> selectByParams(Map<String, Object> map) {
        return fppdMapper.selectByParams(map);
    }
}
