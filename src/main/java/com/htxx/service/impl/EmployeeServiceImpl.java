package com.htxx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.DepartmentEmployee;
import com.htxx.entity.Employee;
import com.htxx.entity.RoleEmployee;
import com.htxx.mapper.DepartmentEmployeeMapper;
import com.htxx.mapper.EmployeeMapper;
import com.htxx.mapper.RoleEmployeeMapper;
import com.htxx.service.EmployeeService;
import com.htxx.util.MD5;
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
 * @Date: 2018/10/26 15:16
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    @Autowired
    private RoleEmployeeMapper roleEmployeeMapper;

    @Override
    public Employee getEmployee(String username) throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("username",username);
        List<Employee> employees = employeeMapper.selectByParams(params);
        if(null != employees && employees.size() > 0) {
            return  employees.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeList(Map<String, Object> params) throws Exception {
        List<Employee> employees = employeeMapper.selectByParams(params);
        return employees;
    }

    @Override
    public ResultMap addEmployee(Employee employee) throws Exception {
        //登录名验证
        Employee input = new Employee();
        input.setLoginCode(employee.getLoginCode());
        Employee _employee = employeeMapper.findByParams(input);
        if(_employee != null && _employee.getId() != null) {
            throw new RuntimeException("登录名重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        input.setEnterpriseId(employee.getEnterpriseId());
        input.setCreateTime(df.format(new Date()));
        input.setName(employee.getName());
        input.setType(employee.getType());
        input.setPhone(employee.getPhone());
        input.setSex(employee.getSex());
        input.setIsAdmin(0);
        input.setIsDel(0);
        input.setStatus(1);
        input.setDesc(employee.getDesc());
        input.setPassword(MD5.md5(employee.getPassword()));
        int dbResult = employeeMapper.insertSelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //插入部门
        JSONArray departments = JSONArray.parseArray(employee.getDepartments());
        if(null != departments) {
            for (int i=0;i<departments.size();i++) {
                DepartmentEmployee departmentEmployee = new DepartmentEmployee();
                departmentEmployee.setDeptId(Long.parseLong(departments.getJSONObject(i).get("id").toString()));
                departmentEmployee.setEmployeeId(input.getId());
                int adbResult = departmentEmployeeMapper.insertSelective(departmentEmployee);
                if(adbResult <=0){
                    throw new RuntimeException(Constent.DB_INSERT_FAILURE);
                }
            }
        }
        //插入岗位
        JSONArray roles = JSONArray.parseArray(employee.getRoles());
        if(null != roles) {
            for (int i=0;i<roles.size();i++) {
                RoleEmployee roleEmployee = new RoleEmployee();
                roleEmployee.setRoleId(Long.parseLong(roles.getJSONObject(i).get("id").toString()));
                roleEmployee.setEmployeeId(input.getId());
                int rdbResult = roleEmployeeMapper.insertSelective(roleEmployee);
                if(rdbResult <=0){
                    throw new RuntimeException(Constent.DB_INSERT_FAILURE);
                }
            }
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateEmployee(Employee employee) {
        //登录名验证
        Employee input = new Employee();
        input.setLoginCode(employee.getLoginCode());
        input.setId(employee.getId());
        Employee _employee = employeeMapper.findByParams(input);
        if(_employee != null && _employee.getId() != null) {
            throw new RuntimeException("登录名重复！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        input.setUpdateTime(df.format(new Date()));
        input.setName(employee.getName());
        input.setPhone(employee.getPhone());
        input.setSex(employee.getSex());
        input.setDesc(employee.getDesc());
        if(null != employee.getPassword() && !StringUtils.isEmpty(String.valueOf(employee.getPassword()))) {
            input.setPassword(MD5.md5(employee.getPassword()));
        }
        int dbResult = employeeMapper.updateByPrimaryKeySelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        //删除原有部门
        DepartmentEmployee delDepartmentEmployee = new DepartmentEmployee();
        delDepartmentEmployee.setEmployeeId(employee.getId());
        int ddbResult = departmentEmployeeMapper.deleteByParams(delDepartmentEmployee);
        //插入部门
        JSONArray departments = JSONArray.parseArray(employee.getDepartments());
        if(null != departments) {
            for (int i=0;i<departments.size();i++) {
                DepartmentEmployee departmentEmployee = new DepartmentEmployee();
                departmentEmployee.setDeptId(Long.parseLong(departments.getJSONObject(i).get("id").toString()));
                departmentEmployee.setEmployeeId(input.getId());
                int adbResult = departmentEmployeeMapper.insertSelective(departmentEmployee);
                if(adbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        }
        //删除原有岗位
        RoleEmployee delRoleEmployee = new RoleEmployee();
        delRoleEmployee.setEmployeeId(employee.getId());
        int rddbResult = roleEmployeeMapper.deleteByParams(delRoleEmployee);
        //插入岗位
        JSONArray roles = JSONArray.parseArray(employee.getRoles());
        if(null != roles) {
            for (int i=0;i<roles.size();i++) {
                RoleEmployee roleEmployee = new RoleEmployee();
                roleEmployee.setRoleId(Long.parseLong(roles.getJSONObject(i).get("id").toString()));
                roleEmployee.setEmployeeId(input.getId());
                int rdbResult = roleEmployeeMapper.insertSelective(roleEmployee);
                if(rdbResult <=0){
                    throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
                }
            }
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public ResultMap updateMyInfo(Employee employee) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        employee.setUpdateTime(df.format(new Date()));
        //设置创建时间
        int dbResult = employeeMapper.updateByPrimaryKeySelective(employee);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public ResultMap updateMyPassword(Employee employee) {
        Employee _employee = employeeMapper.selectByPrimaryKey(employee.getId());
        if(!_employee.getPassword().equals(MD5.md5(employee.getOldPassword()))) {
            throw new RuntimeException("当前密码错误！");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Employee input = new Employee();
        input.setId(employee.getId());
        input.setPassword(MD5.md5(employee.getPassword()));
        input.setUpdateTime(df.format(new Date()));
        //设置创建时间
        int dbResult = employeeMapper.updateByPrimaryKeySelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public ResultMap delEmployee(Long id) {
        try {
            int dbResult = employeeMapper.delEmployee(id);
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
