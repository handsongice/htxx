package com.htxx.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Role;
import com.htxx.entity.RoleAction;
import com.htxx.entity.RoleEmployee;
import com.htxx.mapper.RoleActionMapper;
import com.htxx.mapper.RoleEmployeeMapper;
import com.htxx.mapper.RoleMapper;
import com.htxx.service.RoleService;
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
 * @Date: 2018/11/7 11:54
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleActionMapper roleActionMapper;

    @Autowired
    RoleEmployeeMapper roleEmployeeMapper;

    @Override
    public List<Role> getRoleList(Map<String, Object> params) throws Exception {
        List<Role> roles = roleMapper.selectByParams(params);
        for (Role role:roles) {
            if(null != params.get("employee_id") && !StringUtils.isEmpty(params.get("employee_id").toString())) {
                RoleEmployee input = new RoleEmployee();
                input.setRoleId(role.getId());
                input.setEmployeeId(Long.parseLong(params.get("employee_id").toString()));
                RoleEmployee roleEmployee = roleEmployeeMapper.findByParams(input);
                if(null != roleEmployee && null != roleEmployee.getId()) {
                    role.setChecked(true);
                }
            }
            if(null != params.get("rid") && !StringUtils.isEmpty(params.get("rid").toString())) {
                if(role.getId() == Long.parseLong(params.get("rid").toString())) {
                    role.setChecked(true);
                }
            }
        }
        return roles;
    }

    @Override
    public ResultMap addRole(Role role) throws Exception {
        //用户名验证
        Role input = new Role();
        input.setName(role.getName());
        input.setEnterpriseId(role.getEnterpriseId());
        Role _role = roleMapper.findByParams(input);
        if(_role != null && _role.getId() != null) {
            throw new RuntimeException("用户名重复！");
        }
        input.setType(role.getType());
        input.setDesc(role.getDesc());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        input.setCreateTime(df.format(new Date()));
        int dbResult = roleMapper.insertSelective(input);
        if(dbResult <=0 || null == input.getId() || input.getId() <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        //插入菜单
        JSONArray nodes = JSONArray.parseArray(role.getNodes());
        if(null != nodes) {
            for (int i=0;i<nodes.size();i++) {
                RoleAction roleAction = new RoleAction();
                roleAction.setActionId(Long.parseLong(nodes.getJSONObject(i).get("id").toString()));
                roleAction.setRoleId(input.getId());
                int adbResult = roleActionMapper.insertSelective(roleAction);
                if(adbResult <=0){
                    throw new RuntimeException(Constent.DB_INSERT_FAILURE);
                }
            }
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateRole(Role role) throws Exception {
        //用户名验证
        Role input = new Role();
        input.setName(role.getName());
        input.setEnterpriseId(role.getEnterpriseId());
        input.setId(role.getId());
        Role _role = roleMapper.findByParams(input);
        if(_role != null && _role.getId() != null) {
            throw new RuntimeException("用户名重复！");
        }
        input.setDesc(role.getDesc());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        input.setUpdateTime(df.format(new Date()));
        int dbResult = roleMapper.updateByPrimaryKeySelective(input);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        //删除原有菜单
        RoleAction delInput = new RoleAction();
        delInput.setRoleId(role.getId());
        int ddbResult = roleActionMapper.deleteByParams(delInput);
        //新增菜单
        JSONArray nodes = JSONArray.parseArray(role.getNodes());
        for (int i=0;i<nodes.size();i++) {
            RoleAction roleAction = new RoleAction();
            roleAction.setActionId(Long.parseLong(nodes.getJSONObject(i).get("id").toString()));
            roleAction.setRoleId(role.getId());
            int adbResult = roleActionMapper.insertSelective(roleAction);
            if(adbResult <=0){
                throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
            }
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public ResultMap delRole(Long id) {
        try {
            int dbResult = roleMapper.delRole(id);
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
