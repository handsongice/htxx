package com.htxx.mapper;

import com.htxx.entity.FfLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FfLogEntityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FfLogEntity record);

    int insertSelective(FfLogEntity record);

    FfLogEntity selectByPrimaryKey(Long id);

    List<FfLogEntity> selecyByParams(Map<String, Object> map);

    int updateByPrimaryKeySelective(FfLogEntity record);

    int updateByPrimaryKey(FfLogEntity record);
}