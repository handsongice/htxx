package com.htxx.mapper;

import com.htxx.entity.RoleEmployee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RoleEmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleEmployee record);

    int insertSelective(RoleEmployee record);

    RoleEmployee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleEmployee record);

    int updateByPrimaryKey(RoleEmployee record);

    RoleEmployee findByParams(RoleEmployee record);

    int deleteByParams(RoleEmployee record);
}