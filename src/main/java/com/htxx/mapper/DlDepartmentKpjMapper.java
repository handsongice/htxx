package com.htxx.mapper;

import com.htxx.entity.DlDepartmentKpj;
import com.htxx.entity.DlDepartmentKpjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DlDepartmentKpjMapper {
    int countByExample(DlDepartmentKpjExample example);

    int deleteByExample(DlDepartmentKpjExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlDepartmentKpj record);

    int insertSelective(DlDepartmentKpj record);

    List<DlDepartmentKpj> selectByExample(DlDepartmentKpjExample example);

    DlDepartmentKpj selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlDepartmentKpj record, @Param("example") DlDepartmentKpjExample example);

    int updateByExample(@Param("record") DlDepartmentKpj record, @Param("example") DlDepartmentKpjExample example);

    int updateByPrimaryKeySelective(DlDepartmentKpj record);

    int updateByPrimaryKey(DlDepartmentKpj record);

    List<Long> selectByDeptId(List<Long> deptIds);
}