package com.htxx.mapper;

import com.htxx.entity.DepartmentKpj;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface DepartmentKpjMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartmentKpj record);

    int insertSelective(DepartmentKpj record);

    DepartmentKpj selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartmentKpj record);

    int updateByPrimaryKey(DepartmentKpj record);

    DepartmentKpj findByParams(DepartmentKpj record);

    int deleteByParams(DepartmentKpj record);

    List<DepartmentKpj> selectByParams(Map<String, Object> map);
}