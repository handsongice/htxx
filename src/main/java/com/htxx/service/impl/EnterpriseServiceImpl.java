package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Employee;
import com.htxx.entity.Enterprise;
import com.htxx.entity.Kpjxx;
import com.htxx.mapper.EmployeeMapper;
import com.htxx.mapper.EnterpriseMapper;
import com.htxx.mapper.KpjxxMapper;
import com.htxx.service.EnterpriseService;
import com.htxx.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 13:40
 */
@Service
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private KpjxxMapper kpjxxMapper;

    @Override
    public List<Enterprise> getEnterpriseList(Map<String, Object> params) throws Exception {
        List<Enterprise> enterprises = enterpriseMapper.selectByParams(params);
        return enterprises;
    }

    @Override
    public ResultMap addEnterprise(Enterprise enterprise) {
        //用户名验证
        Enterprise input = new Enterprise();
        input.setName(enterprise.getName());
        Enterprise _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException("用户名重复！");
        }
        //税号验证
        input = new Enterprise();
        input.setTaxNum(enterprise.getTaxNum());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException("税号重复！");
        }
        //账号验证
        input = new Enterprise();
        input.setLoginCode(enterprise.getLoginCode());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            throw new RuntimeException("登录账号重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        enterprise.setCreateTime(df.format(new Date()));
        int dbResult = enterpriseMapper.insertSelective(enterprise);
        if(dbResult <=0 || null == enterprise.getId() || enterprise.getId() <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //插入员工表
        Employee _employee = new Employee();
        _employee.setLoginCode(enterprise.getLoginCode());
        if(null != enterprise.getPassword()) {
            _employee.setPassword(MD5.md5(enterprise.getPassword()));
        } else {
            _employee.setPassword(MD5.md5("123456"));
        }
        _employee.setEnterpriseId(enterprise.getId());
        _employee.setType(2);
        _employee.setName(enterprise.getName());
        _employee.setIsAdmin(1);
        _employee.setCreateTime(df.format(new Date()));
        int edbResult = employeeMapper.insertSelective(_employee);
        if(edbResult <=0){
            throw new RuntimeException("管理员添加失败");
        }
        //开票机
        Kpjxx kpjxx = new Kpjxx();
        for (int i=0;i<enterprise.getNum();i++) {
            StringBuffer sb = new StringBuffer();
            kpjxx.setName(sb.append("开票机").append(i).toString());
            kpjxx.setNickName(sb.toString());
            kpjxx.setEnterpriseId(enterprise.getId());
            kpjxx.setCode(String.valueOf(i));
            kpjxx.setCreateTime(df.format(new Date()));
            int idbResult = kpjxxMapper.insertSelective(kpjxx);
            if(edbResult <=0){
                throw new RuntimeException("开票机创建失败");
            }
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateEnterprise(Enterprise enterprise) {
        //用户名验证
        Enterprise input = new Enterprise();
        input.setName(enterprise.getName());
        input.setId(enterprise.getId());
        Enterprise _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            return ResultMap.fail("用户名重复！");
        }
        //税号验证
        input = new Enterprise();
        input.setTaxNum(enterprise.getTaxNum());
        input.setId(enterprise.getId());
        _enterprise = enterpriseMapper.findByParams(input);
        if(_enterprise != null && _enterprise.getId() != null) {
            return ResultMap.fail("税号重复！");
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //设置创建时间
            enterprise.setUpdateTime(df.format(new Date()));
            int dbResult = enterpriseMapper.updateByPrimaryKeySelective(enterprise);
            if(dbResult >0){
                return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
            }else{
                return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultMap.fail(Constent.DB_UPDATE_FAILURE);
        }
    }

    @Override
    public Enterprise getEnterpriseById(Long id) {
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(id);
        return enterprise;
    }

    @Override
    public ResultMap delEnterprise(Long id) {
        try {
            int dbResult = enterpriseMapper.delEnterprise(id);
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
