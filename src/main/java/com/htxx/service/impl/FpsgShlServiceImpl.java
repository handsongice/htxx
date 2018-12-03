package com.htxx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.FpsgShl;
import com.htxx.mapper.FpsgShlMapper;
import com.htxx.service.FpsgShlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/12 13:05
 */
@Service
@Transactional
public class FpsgShlServiceImpl implements FpsgShlService {
    @Autowired
    FpsgShlMapper fpsgShlMapper;

    @Override
    public List<FpsgShl> getFpsgShlList(Map<String, Object> params) throws Exception {
        List<FpsgShl> fpsgShls = fpsgShlMapper.selectByParams(params);
        return fpsgShls;
    }

    @Override
    public ResultMap addFpsgShl(FpsgShl fpsgShl) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        fpsgShl.setCreateTime(new Date());
        int dbResult = fpsgShlMapper.insertSelective(fpsgShl);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        if("line".equals(fpsgShl.getStyle())) {
            //更新父节点
            FpsgShl _fpsgShl1 = new FpsgShl();
            String[] froms = null;
            String[] tos = null;
            Long _pid = null;
            Long _id = null;
            if(!StringUtils.isEmpty(fpsgShl.getFrom())) {
                froms = fpsgShl.getFrom().split("_");
                _pid = Long.parseLong(froms[2]);
                _fpsgShl1.setId(_pid);
            }
            if(!StringUtils.isEmpty(fpsgShl.getTo())) {
                tos = fpsgShl.getTo().split("_");
                _id = Long.parseLong(tos[2]);
                _fpsgShl1.setNextId(_id);
            }
            _fpsgShl1.setLineId(fpsgShl.getId());
            _fpsgShl1.setUpdateTime(new Date());
            int pdbResult = fpsgShlMapper.updateByPrimaryKeySelective(_fpsgShl1);
            if(pdbResult <=0){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
            //更新子节点
            _fpsgShl1 = new FpsgShl();
            _fpsgShl1.setId(_id);
            _fpsgShl1.setParentId(_pid);
            _fpsgShl1.setUpdateTime(new Date());
            _fpsgShl1.setLineId(fpsgShl.getId());
            int sdbResult = fpsgShlMapper.updateByPrimaryKeySelective(_fpsgShl1);
            if(sdbResult <=0){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
        }
        return ResultMap.build(200,Constent.DB_INSERT_SUCCESS,fpsgShl.getId());
    }

    @Override
    public ResultMap updateFpsgShl(FpsgShl fpsgShl) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        fpsgShl.setUpdateTime(new Date());
        int dbResult = fpsgShlMapper.updateByPrimaryKeySelective(fpsgShl);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public FpsgShl getFpsgShlById(Long id) {
        return fpsgShlMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultMap delFpsgShl(Long id) {
        FpsgShl fpsgShl = fpsgShlMapper.selectByPrimaryKey(id);
        if(null == fpsgShl) {
            throw new RuntimeException("节点不存在！");
        }
        if("node".equals(fpsgShl.getStyle())) {
            if(null != fpsgShl.getLineId() && fpsgShl.getLineId() > 0) {
                //删除线
                int dbResult = fpsgShlMapper.deleteByPrimaryKey(fpsgShl.getLineId());
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_DELETE_FAILURE);
                }
            }
            if(null != fpsgShl.getParentId() && fpsgShl.getParentId() > 0) {
                //更新父节点
                FpsgShl ufpsgShl1 = new FpsgShl();
                ufpsgShl1.setId(fpsgShl.getParentId());
                ufpsgShl1.setNextId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = fpsgShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
            if(null != fpsgShl.getNextId() && fpsgShl.getNextId() > 0) {
                //更新子节点
                FpsgShl ufpsgShl1 = new FpsgShl();
                ufpsgShl1.setId(fpsgShl.getNextId());
                ufpsgShl1.setParentId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = fpsgShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        } else if ("line".equals(fpsgShl.getStyle())) {
            if(StringUtils.isNotEmpty(fpsgShl.getFrom())) {
                String[] _froms = fpsgShl.getFrom().split("_");
                //更新父节点
                FpsgShl ufpsgShl1 = new FpsgShl();
                ufpsgShl1.setId(Long.parseLong(_froms[2]));
                ufpsgShl1.setNextId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = fpsgShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
            if(StringUtils.isNotEmpty(fpsgShl.getTo())) {
                String[] _tos = fpsgShl.getTo().split("_");
                //更新子节点
                FpsgShl ufpsgShl1 = new FpsgShl();
                ufpsgShl1.setId(Long.parseLong(_tos[2]));
                ufpsgShl1.setParentId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = fpsgShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        }
        int dbResult = fpsgShlMapper.deleteByPrimaryKey(id);
        if(dbResult >0){
            return ResultMap.success(Constent.DB_DELETE_SUCCESS);
        }else{
            throw new RuntimeException(Constent.DB_DELETE_FAILURE);

        }
    }
}
