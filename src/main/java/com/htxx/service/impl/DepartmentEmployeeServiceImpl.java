package com.htxx.service.impl;

import com.htxx.entity.Department;
import com.htxx.entity.DepartmentEmployee;
import com.htxx.entity.Employee;
import com.htxx.mapper.DepartmentEmployeeMapper;
import com.htxx.service.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Administrator
 * @date: 2018-11-14
 * @description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    @Autowired
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentEmployeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DepartmentEmployee record) {
        return departmentEmployeeMapper.insert(record);
    }

    @Override
    public int insertSelective(DepartmentEmployee record) {
        return departmentEmployeeMapper.insertSelective(record);
    }

    @Override
    public DepartmentEmployee selectByPrimaryKey(Long id) {
        return departmentEmployeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DepartmentEmployee record) {
        return departmentEmployeeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DepartmentEmployee record) {
        return departmentEmployeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public DepartmentEmployee findByParams(DepartmentEmployee record) {
        return departmentEmployeeMapper.findByParams(record);
    }

    @Override
    public int deleteByParams(DepartmentEmployee record) {
        return departmentEmployeeMapper.deleteByParams(record);
    }

    @Override
    public Employee selectEmpDeptsList(Employee employee) {
        return departmentEmployeeMapper.selectEmpDeptsList(employee);
    }

    @Override
    public Department selectDeptEmpsList(Department department) {
        return departmentEmployeeMapper.selectDeptEmpsList(department);
    }
}
