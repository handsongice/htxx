package com.htxx.mapper;

import com.htxx.entity.RkEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RkEntityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RkEntity record);

    int insertSelective(RkEntity record);

    RkEntity selectByPrimaryKey(Long id);

    List<RkEntity> selectByParams(Map<String, Object> map);

    List<RkEntity> selectFfList(Map<String, Object> map);

    int updateByPrimaryKeySelective(RkEntity record);

    int updateByPrimaryKey(RkEntity record);
}