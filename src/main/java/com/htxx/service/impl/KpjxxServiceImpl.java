package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.DepartmentKpj;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Kpjxx;
import com.htxx.mapper.DepartmentKpjMapper;
import com.htxx.mapper.EnterpriseMapper;
import com.htxx.mapper.KpjxxMapper;
import com.htxx.service.KpjxxService;
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
 * @Date: 2018/11/5 13:50
 */
@Service
@Transactional
public class KpjxxServiceImpl implements KpjxxService {

    @Autowired
    private KpjxxMapper kpjxxMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private DepartmentKpjMapper departmentKpjMapper;

    @Override
    public List<Kpjxx> getKpjxxList(Map<String, Object> params) throws Exception {
        List<Kpjxx> kpjxxes = kpjxxMapper.selectByParams(params);
        if (null != params.get("dept_id") && !StringUtils.isEmpty(params.get("dept_id").toString())) {
            for (Kpjxx kpjxx : kpjxxes) {
                DepartmentKpj input = new DepartmentKpj();
                input.setKpjId(kpjxx.getId());
                input.setDeptId(Long.parseLong(params.get("dept_id").toString()));
                DepartmentKpj departmentKpj = departmentKpjMapper.findByParams(input);
                if (null != departmentKpj && null != departmentKpj.getId()) {
                    kpjxx.setChecked(true);
                }
            }
        }
        return kpjxxes;
    }

    @Override
    public ResultMap addKpjxx(Kpjxx kpjxx) throws Exception {
        Enterprise _enterprise = enterpriseMapper.selectByPrimaryKey(kpjxx.getEnterpriseId());
        if (_enterprise == null || _enterprise.getId() == null) {
            throw new RuntimeException("用户名重复！");
        }
        kpjxx.setCode(_enterprise.getNum().toString());
        StringBuffer sb = new StringBuffer();
        kpjxx.setName(sb.append("开票机").append(_enterprise.getNum()).toString());
        kpjxx.setNickName(sb.toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        kpjxx.setCreateTime(df.format(new Date()));
        kpjxx.setIsDel(0);
        //新增开票机
        int dbResult = kpjxxMapper.insertSelective(kpjxx);
        if (dbResult <= 0) {
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //更新num
        Enterprise input = new Enterprise();
        input.setId(_enterprise.getId());
        Integer _num = _enterprise.getNum() + 1;
        input.setNum(_num);
        input.setUpdateTime(df.format(new Date()));
        int udbResult = enterpriseMapper.updateByPrimaryKeySelective(input);
        if (udbResult <= 0) {
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateKpjxx(Kpjxx kpjxx) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            kpjxx.setUpdateTime(df.format(new Date()));
            int dbResult = kpjxxMapper.updateByPrimaryKeySelective(kpjxx);
            if (dbResult > 0) {
                return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
            } else {
                return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
        }
    }

    @Override
    public Kpjxx getKpjxxById(Long id) {
        return null;
    }

    @Override
    public ResultMap delKpjxx(Long id) {
        return null;
    }

    @Override
    public List<Kpjxx> selectKpjDeptByParams(Map<String, Object> map) {
        return kpjxxMapper.selectKpjDeptByParams(map);
    }
}
