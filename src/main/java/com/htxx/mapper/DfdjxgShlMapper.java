package com.htxx.mapper;

import com.htxx.entity.DfdjxgShl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Component
@Repository
public interface DfdjxgShlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DfdjxgShl record);

    int insertSelective(DfdjxgShl record);

    DfdjxgShl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DfdjxgShl record);

    int updateByPrimaryKey(DfdjxgShl record);

    List<DfdjxgShl> selectByParams(Map<String, Object> map);

    DfdjxgShl findByParams(DfdjxgShl record);
}