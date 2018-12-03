package com.htxx.mapper;

import com.htxx.entity.DlRoleEmployee;
import com.htxx.entity.DlRoleEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlRoleEmployeeMapper {
    int countByExample(DlRoleEmployeeExample example);

    int deleteByExample(DlRoleEmployeeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlRoleEmployee record);

    int insertSelective(DlRoleEmployee record);

    List<DlRoleEmployee> selectByExample(DlRoleEmployeeExample example);

    DlRoleEmployee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlRoleEmployee record, @Param("example") DlRoleEmployeeExample example);

    int updateByExample(@Param("record") DlRoleEmployee record, @Param("example") DlRoleEmployeeExample example);

    int updateByPrimaryKeySelective(DlRoleEmployee record);

    int updateByPrimaryKey(DlRoleEmployee record);
}