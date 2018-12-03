package com.htxx.mapper;

import com.htxx.entity.FpsgShLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FpsgShLogEntityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FpsgShLogEntity record);

    int insertSelective(FpsgShLogEntity record);

    FpsgShLogEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FpsgShLogEntity record);

    int updateByPrimaryKey(FpsgShLogEntity record);

    List<FpsgShLogEntity> selectByParams(Map<String, Object> paramsMap);
}