package com.htxx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Department;
import com.htxx.entity.DepartmentEmployee;
import com.htxx.entity.DepartmentKpj;
import com.htxx.mapper.DepartmentEmployeeMapper;
import com.htxx.mapper.DepartmentKpjMapper;
import com.htxx.mapper.DepartmentMapper;
import com.htxx.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2018/11/6 10:24
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DepartmentEmployeeMapper departmentEmployeeMapper;

    @Autowired
    DepartmentKpjMapper departmentKpjMapper;

    @Override
    public List<Department> getDepartmentList(Map<String, Object> params) throws Exception {
        List<Department> departments = departmentMapper.selectByParams(params);

        for (Department department:departments) {
            if(null != params.get("employee_id") && !StringUtils.isEmpty(params.get("employee_id").toString())) {
                DepartmentEmployee input = new DepartmentEmployee();
                input.setDeptId(department.getId());
                input.setEmployeeId(Long.parseLong(params.get("employee_id").toString()));
                DepartmentEmployee departmentEmployee = departmentEmployeeMapper.findByParams(input);
                if(null != departmentEmployee && null != departmentEmployee.getId()) {
                    department.setChecked(true);
                }
            }
            Map<String, Object> _params = new HashMap<>();
            _params.put("dept_id",department.getId());
            List<DepartmentKpj> departmentKpjs = departmentKpjMapper.selectByParams(_params);
            if(null != departmentKpjs && departmentKpjs.size() >0) {
                StringBuffer sb = new StringBuffer();
                for (int i=0;i<departmentKpjs.size();i++) {
                    sb.append(departmentKpjs.get(i).getKpjName());
                    if((i+1)<departmentKpjs.size()){
                        sb.append(",");
                    }
                }
                department.setKpjxx(sb.toString());
            }

        }

        return departments;
    }

    @Override
    public ResultMap addDepartment(Department department) throws Exception {
        //用户名验证
        Department input = new Department();
        input.setName(department.getName());
        input.setEnterpriseId(department.getEnterpriseId());
        Department _department = departmentMapper.findByParams(input);
        if(_department != null && _department.getId() != null) {
            throw new RuntimeException("用户名重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        department.setCreateTime(df.format(new Date()));
        Long maxSort = departmentMapper.maxSort(department.getParentId());
        if(null != maxSort) {
            department.setSort(maxSort.intValue()+1);
        } else {
            department.setSort(new Integer(1));
        }
        String maxTreeCode = departmentMapper.maxTreeCode(department.getParentId());
        if(!StringUtils.isEmpty(maxTreeCode)) {
            department.setTreeCode(String.valueOf(Integer.parseInt(maxTreeCode)+1));
        } else {
            if(department.getParentId() == 0) {
                department.setTreeCode("10001");
            }
            HashMap<String, Object> first = new HashMap<String, Object>();
            first.put("id",department.getParentId());
            List<Department> departments = departmentMapper.selectByParams(first);
            if(null != departments && departments.size() > 0) {
                department.setTreeCode(departments.get(0).getTreeCode()+"10001");
            }
        }
        int dbResult = departmentMapper.insertSelective(department);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //插入开票机
        JSONArray nodes = JSONArray.parseArray(department.getNodes());
        if(null != nodes) {
            for (int i=0;i<nodes.size();i++) {
                DepartmentKpj departmentKpj = new DepartmentKpj();
                departmentKpj.setKpjId(Long.parseLong(nodes.getJSONObject(i).get("id").toString()));
                departmentKpj.setDeptId(department.getId());
                int adbResult = departmentKpjMapper.insertSelective(departmentKpj);
                if(adbResult <=0){
                    throw new RuntimeException(Constent.DB_INSERT_FAILURE);
                }
            }
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateDepartment(Department department) throws Exception {
        //用户名验证
        Department input = new Department();
        input.setName(department.getName());
        input.setId(department.getId());
        Department _department = departmentMapper.findByParams(input);
        if(_department != null && _department.getId() != null) {
            throw new RuntimeException("用户名重复！");
        }
        if(department.isUpdateParent()) {
            Map<String, Object> params = new HashMap<>();
            params.put("enterprise_id",department.getEnterpriseId());
            params.put("tree_code2",department.getTreeCode());
            params.put("no_id",department.getId());
            List<Department> departments = departmentMapper.selectByParams(params);
            if(null != departments && departments.size() >0) {
                throw new RuntimeException("有下级不能更改上级部门！");
            }
            Long maxSort = departmentMapper.maxSort(department.getParentId());
            if(null != maxSort) {
                input.setSort(maxSort.intValue()+1);
            } else {
                input.setSort(new Integer(1));
            }
            String maxTreeCode = departmentMapper.maxTreeCode(department.getParentId());
            if(!StringUtils.isEmpty(maxTreeCode)) {
                input.setTreeCode(String.valueOf(Integer.parseInt(maxTreeCode)+1));
            } else {
                if(department.getParentId() == 0) {
                    input.setTreeCode("10001");
                }
                HashMap<String, Object> first = new HashMap<String, Object>();
                first.put("id",department.getParentId());
                List<Department> _departments = departmentMapper.selectByParams(first);
                if(null != _departments && _departments.size() > 0) {
                    input.setTreeCode(_departments.get(0).getTreeCode()+"10001");
                }
            }
            input.setParentId(department.getParentId());
        }
        input.setLeader(department.getLeader());
        input.setPhone(department.getPhone());
        input.setDesc(department.getDesc());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        input.setUpdateTime(df.format(new Date()));
        int dbResult = departmentMapper.updateByPrimaryKeySelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        //删除原有开票机
        DepartmentKpj delInput = new DepartmentKpj();
        delInput.setDeptId(department.getId());
        int ddbResult = departmentKpjMapper.deleteByParams(delInput);
        //插入开票机
        JSONArray nodes = JSONArray.parseArray(department.getNodes());
        if(null != nodes) {
            for (int i=0;i<nodes.size();i++) {
                DepartmentKpj departmentKpj = new DepartmentKpj();
                departmentKpj.setKpjId(Long.parseLong(nodes.getJSONObject(i).get("id").toString()));
                departmentKpj.setDeptId(department.getId());
                int adbResult = departmentKpjMapper.insertSelective(departmentKpj);
                if(adbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_SUCCESS);
                }
            }
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Department getDepartmentById(Long id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public ResultMap delDepartment(Long id) {
        try {
            Department department = departmentMapper.selectByPrimaryKey(id);
            Map<String, Object> params = new HashMap<>();
            params.put("enterprise_id",department.getEnterpriseId());
            params.put("tree_code2",department.getTreeCode());
            params.put("no_id",department.getId());
            List<Department> departments = departmentMapper.selectByParams(params);
            if(null != departments && departments.size() >0) {
                return ResultMap.fail("有下级部门不能删除！");
            }
            int dbResult = departmentMapper.delDepartment(id);
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
