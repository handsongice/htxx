package com.htxx.mapper;

import com.htxx.entity.RkLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RkLogEntityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RkLogEntity record);

    int insertSelective(RkLogEntity record);

    RkLogEntity selectByPrimaryKey(Long id);

    List<RkLogEntity> selectByParams(Map<String, Object> map);

    int updateByPrimaryKeySelective(RkLogEntity record);

    int updateByPrimaryKey(RkLogEntity record);
}