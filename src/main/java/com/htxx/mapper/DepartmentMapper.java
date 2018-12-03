package com.htxx.mapper;

import com.htxx.entity.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectByParams(Map<String, Object> map);

    Department findByParams(Department record);

    Long maxSort(Long parentId);

    String maxTreeCode(Long parentId);

    int delDepartment(Long id);
}