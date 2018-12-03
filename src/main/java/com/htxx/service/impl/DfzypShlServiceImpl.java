package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.DfzypShl;
import com.htxx.entity.FpsgShl;
import com.htxx.mapper.DfzypShlMapper;
import com.htxx.mapper.FpsgShlMapper;
import com.htxx.service.DfzypShlService;
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
public class DfzypShlServiceImpl implements DfzypShlService {
    @Autowired
    DfzypShlMapper dfzypShlMapper;

    @Override
    public List<DfzypShl> getDfzypShlList(Map<String, Object> params) throws Exception {
        List<DfzypShl> dfzypShls = dfzypShlMapper.selectByParams(params);
        return dfzypShls;
    }

    @Override
    public ResultMap addDfzypShl(DfzypShl dfzypShl) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        dfzypShl.setCreateTime(df.format(new Date()));
        int dbResult = dfzypShlMapper.insertSelective(dfzypShl);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        if("line".equals(dfzypShl.getStyle())) {
            //更新父节点
            DfzypShl _fpsgShl1 = new DfzypShl();
            String[] froms = null;
            String[] tos = null;
            Long _pid = null;
            Long _id = null;
            if(!StringUtils.isEmpty(dfzypShl.getFrom())) {
                froms = dfzypShl.getFrom().split("_");
                _pid = Long.parseLong(froms[2]);
                _fpsgShl1.setId(_pid);
            }
            if(!StringUtils.isEmpty(dfzypShl.getTo())) {
                tos = dfzypShl.getTo().split("_");
                _id = Long.parseLong(tos[2]);
                _fpsgShl1.setNextId(_id);
            }
            _fpsgShl1.setLineId(dfzypShl.getId());
            _fpsgShl1.setUpdateTime(df.format(new Date()));
            int pdbResult = dfzypShlMapper.updateByPrimaryKeySelective(_fpsgShl1);
            if(pdbResult <=0){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
            //更新子节点
            _fpsgShl1 = new DfzypShl();
            _fpsgShl1.setId(_id);
            _fpsgShl1.setParentId(_pid);
            _fpsgShl1.setUpdateTime(df.format(new Date()));
            _fpsgShl1.setLineId(dfzypShl.getId());
            int sdbResult = dfzypShlMapper.updateByPrimaryKeySelective(_fpsgShl1);
            if(sdbResult <=0){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
        }
        return ResultMap.build(200,Constent.DB_INSERT_SUCCESS,dfzypShl.getId());
    }

    @Override
    public ResultMap updateDfzypShl(DfzypShl dfzypShl) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        dfzypShl.setUpdateTime(df.format(new Date()));
        int dbResult = dfzypShlMapper.updateByPrimaryKeySelective(dfzypShl);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public DfzypShl getDfzypShlById(Long id) {
        return dfzypShlMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultMap delDfzypShl(Long id) {
        DfzypShl dfzypShl = dfzypShlMapper.selectByPrimaryKey(id);
        if(null == dfzypShl) {
            throw new RuntimeException("节点不存在！");
        }
        if("node".equals(dfzypShl.getStyle())) {
            if(null != dfzypShl.getLineId() && dfzypShl.getLineId() > 0) {
                //删除线
                int dbResult = dfzypShlMapper.deleteByPrimaryKey(dfzypShl.getLineId());
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_DELETE_FAILURE);
                }
            }
            if(null != dfzypShl.getParentId() && dfzypShl.getParentId() > 0) {
                //更新父节点
                DfzypShl ufpsgShl1 = new DfzypShl();
                ufpsgShl1.setId(dfzypShl.getParentId());
                ufpsgShl1.setNextId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = dfzypShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
            if(null != dfzypShl.getNextId() && dfzypShl.getNextId() > 0) {
                //更新子节点
                DfzypShl ufpsgShl1 = new DfzypShl();
                ufpsgShl1.setId(dfzypShl.getNextId());
                ufpsgShl1.setParentId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = dfzypShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        } else if ("line".equals(dfzypShl.getStyle())) {
            if(StringUtils.isNotEmpty(dfzypShl.getFrom())) {
                String[] _froms = dfzypShl.getFrom().split("_");
                //更新父节点
                DfzypShl ufpsgShl1 = new DfzypShl();
                ufpsgShl1.setId(Long.parseLong(_froms[2]));
                ufpsgShl1.setNextId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = dfzypShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
            if(StringUtils.isNotEmpty(dfzypShl.getTo())) {
                String[] _tos = dfzypShl.getTo().split("_");
                //更新子节点
                DfzypShl ufpsgShl1 = new DfzypShl();
                ufpsgShl1.setId(Long.parseLong(_tos[2]));
                ufpsgShl1.setParentId(new Long(0));
                ufpsgShl1.setLineId(new Long(0));
                int dbResult = dfzypShlMapper.updateByPrimaryKeySelective(ufpsgShl1);
                if(dbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        }
        int dbResult = dfzypShlMapper.deleteByPrimaryKey(id);
        if(dbResult >0){
            return ResultMap.success(Constent.DB_DELETE_SUCCESS);
        }else{
            throw new RuntimeException(Constent.DB_DELETE_FAILURE);

        }
    }
}
