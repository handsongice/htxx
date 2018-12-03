package com.htxx.service.impl;

import com.htxx.common.BaseResultData;
import com.htxx.entity.Fpsg;
import com.htxx.mapper.FpsgMapper;
import com.htxx.service.FpsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 发票审购ServiceImpl
 *
 * @author zhanghs
 * @date 2018/11/5
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FpsgServiceImpl implements FpsgService {

    @Autowired
    private FpsgMapper fpsgMapper;

    @Override
    public int deleteByPrimaryKey(Fpsg key) {
        return fpsgMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(Fpsg record) {
        record.setCreateTime(new Date());
        record.setStatus("0");
        return fpsgMapper.insert(record);
    }

    @Override
    public int insertSelective(Fpsg record) {
        return fpsgMapper.insertSelective(record);
    }

    @Override
    public Fpsg selectByPrimaryKey(Fpsg key) {
        return fpsgMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByPrimaryKeySelective(Fpsg record) {
        return fpsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Fpsg record) {
        return fpsgMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Fpsg> selectByParams(Map<String, Object> map) {
        return fpsgMapper.selectByParams(map);
    }

    @Override
    public BaseResultData updateReviewPass() {
        return null;
    }

    @Override
    public BaseResultData updateReviewReject() {
        return null;
    }
}
