package com.htxx.mapper;

import com.htxx.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByParams(Map<String, Object> map);

    Role findByParams(Role record);

    int delRole(Long id);
}