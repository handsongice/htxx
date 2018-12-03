package com.htxx.mapper;

import com.htxx.entity.FpsgShl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FpsgShlMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FpsgShl record);

    int insertSelective(FpsgShl record);

    FpsgShl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FpsgShl record);

    int updateByPrimaryKey(FpsgShl record);

    List<FpsgShl> selectByParams(Map<String, Object> map);
}