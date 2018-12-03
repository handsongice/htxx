package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Department;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/6 10:19
 */
public interface DepartmentService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Department> getDepartmentList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param department
     * @return
     */
    ResultMap addDepartment(Department department) throws Exception;

    /**
     * 更新数据
     * @param department
     * @return
     */
    ResultMap updateDepartment(Department department) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    Department getDepartmentById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delDepartment(Long id);
}
