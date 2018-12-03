package com.htxx.mapper;

import com.htxx.entity.FfEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FfEntityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FfEntity record);

    int insertSelective(FfEntity record);

    FfEntity selectByPrimaryKey(Long id);

    List<FfEntity> selectFfList(Map<String, Object> map);

    int updateByPrimaryKeySelective(FfEntity record);

    int updateByPrimaryKey(FfEntity record);
}