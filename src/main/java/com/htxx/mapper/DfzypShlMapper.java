package com.htxx.mapper;

import com.htxx.entity.DfzypShl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
@Repository
public interface DfzypShlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DfzypShl record);

    int insertSelective(DfzypShl record);

    DfzypShl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DfzypShl record);

    int updateByPrimaryKey(DfzypShl record);

    List<DfzypShl> selectByParams(Map<String, Object> map);

    DfzypShl findByParams(DfzypShl record);
}