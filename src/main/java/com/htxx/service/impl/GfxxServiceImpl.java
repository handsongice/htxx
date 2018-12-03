package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Gfxx;
import com.htxx.mapper.GfxxMapper;
import com.htxx.service.GfxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 9:00
 */
@Service
@Transactional
public class GfxxServiceImpl implements GfxxService {
    @Autowired
    GfxxMapper gfxxMapper;

    @Override
    public List<Gfxx> getGfxxList(Map<String, Object> params) throws Exception {
        List<Gfxx> gfxxes = gfxxMapper.selectByParams(params);
        return gfxxes;
    }

    @Override
    public ResultMap addGfxx(Gfxx gfxx) throws Exception {
        //购方名称验证
        Gfxx input = new Gfxx();
        input.setGfmc(gfxx.getGfmc());
        input.setEnterpriseId(gfxx.getEnterpriseId());
        Gfxx _gfxx = gfxxMapper.findByParams(input);
        if(_gfxx != null && _gfxx.getId() != null) {
            throw new RuntimeException("购方名称重复！");
        }
        //购方税号验证
        input = new Gfxx();
        input.setGfsh(gfxx.getGfsh());
        input.setEnterpriseId(gfxx.getEnterpriseId());
        _gfxx = gfxxMapper.findByParams(input);
        if(_gfxx != null && _gfxx.getId() != null) {
            throw new RuntimeException("购方税号重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        gfxx.setCreateTime(df.format(new Date()));
        int dbResult = gfxxMapper.insertSelective(gfxx);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateGfxx(Gfxx gfxx) throws Exception {
        //购方名称验证
        Gfxx input = new Gfxx();
        input.setId(gfxx.getId());
        input.setGfmc(gfxx.getGfmc());
        input.setEnterpriseId(gfxx.getEnterpriseId());
        Gfxx _gfxx = gfxxMapper.findByParams(input);
        if(_gfxx != null && _gfxx.getId() != null) {
            throw new RuntimeException("购方名称重复！");
        }
        //购方税号验证
        input = new Gfxx();
        input.setId(gfxx.getId());
        input.setGfsh(gfxx.getGfsh());
        input.setEnterpriseId(gfxx.getEnterpriseId());
        _gfxx = gfxxMapper.findByParams(input);
        if(_gfxx != null && _gfxx.getId() != null) {
            throw new RuntimeException("购方税号重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        gfxx.setUpdateTime(df.format(new Date()));
        int dbResult = gfxxMapper.updateByPrimaryKeySelective(gfxx);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Gfxx getGfxxById(Long id) {
        Gfxx gfxx = gfxxMapper.selectByPrimaryKey(id);
        return gfxx;
    }

    @Override
    public ResultMap delGfxx(Long id) {
        try {
            int dbResult = gfxxMapper.delGfxx(id);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_DELETE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_DELETE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }
}
