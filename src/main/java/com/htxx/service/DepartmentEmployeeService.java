package com.htxx.service;

import com.htxx.entity.Department;
import com.htxx.entity.DepartmentEmployee;
import com.htxx.entity.Employee;

public interface DepartmentEmployeeService {

    int deleteByPrimaryKey(Long id);

    int insert(DepartmentEmployee record);

    int insertSelective(DepartmentEmployee record);

    DepartmentEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentEmployee record);

    int updateByPrimaryKey(DepartmentEmployee record);

    DepartmentEmployee findByParams(DepartmentEmployee record);

    int deleteByParams(DepartmentEmployee record);

    Employee selectEmpDeptsList(Employee employee);

    Department selectDeptEmpsList(Department department);
}