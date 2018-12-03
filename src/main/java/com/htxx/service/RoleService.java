package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/7 10:23
 */
public interface RoleService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Role> getRoleList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param role
     * @return
     */
    ResultMap addRole(Role role) throws Exception;

    /**
     * 更新数据
     * @param role
     * @return
     */
    ResultMap updateRole(Role role) throws Exception;

    /**
     * 获取信息
     * @param id
     * @return
     */
    Role getRoleById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delRole(Long id);
}
