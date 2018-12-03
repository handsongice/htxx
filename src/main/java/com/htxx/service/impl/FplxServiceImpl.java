package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Fplx;
import com.htxx.mapper.FplxMapper;
import com.htxx.service.FplxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/9 13:01
 */
@Service
@Transactional
public class FplxServiceImpl implements FplxService {
    @Autowired
    FplxMapper fplxMapper;

    @Override
    public List<Fplx> getFplxList(Map<String, Object> params) throws Exception {
        List<Fplx> fplxes = fplxMapper.selectByParams(params);
        return fplxes;
    }

    @Override
    public ResultMap addFplx(Fplx fplx) throws Exception {
        //名称验证
        Fplx input = new Fplx();
        input.setName(fplx.getName());
        input.setEnterpriseId(fplx.getEnterpriseId());
        Fplx _fplx = fplxMapper.findByParams(input);
        if (_fplx != null && _fplx.getId() != null) {
            throw new RuntimeException("名称重复！");
        }
        //编码验证
        input = new Fplx();
        input.setCode(fplx.getCode());
        input.setEnterpriseId(fplx.getEnterpriseId());
        _fplx = fplxMapper.findByParams(input);
        if (_fplx != null && _fplx.getId() != null) {
            throw new RuntimeException("编码重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        fplx.setCreateTime(df.format(new Date()));
        int dbResult = fplxMapper.insertSelective(fplx);
        if (dbResult <= 0) {
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateFplx(Fplx fplx) throws Exception {
        //名称验证
        Fplx input = new Fplx();
        input.setId(fplx.getId());
        input.setName(fplx.getName());
        input.setEnterpriseId(fplx.getEnterpriseId());
        Fplx _fplx = fplxMapper.findByParams(input);
        if (_fplx != null && _fplx.getId() != null) {
            throw new RuntimeException("名称重复！");
        }
        //编码验证
        input = new Fplx();
        input.setId(fplx.getId());
        input.setCode(fplx.getCode());
        input.setEnterpriseId(fplx.getEnterpriseId());
        _fplx = fplxMapper.findByParams(input);
        if (_fplx != null && _fplx.getId() != null) {
            throw new RuntimeException("编码重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        fplx.setUpdateTime(df.format(new Date()));
        int dbResult = fplxMapper.updateByPrimaryKeySelective(fplx);
        if (dbResult <= 0) {
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Fplx getFplxById(Long id) {
        Fplx fplx = fplxMapper.selectByPrimaryKey(id);
        return fplx;
    }

    @Override
    public ResultMap delFplx(Long id) {
        try {
            int dbResult = fplxMapper.delFplx(id);
            if (dbResult > 0) {
                return ResultMap.success(Constent.DB_DELETE_SUCCESS);
            } else {
                return ResultMap.fail(Constent.DB_DELETE_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_DELETE_FAILURE);
        }
    }

    @Override
    public List<Fplx> selectByParams(Fplx fplx) {
        return fplxMapper.selectByParams(fplx);
    }
}
