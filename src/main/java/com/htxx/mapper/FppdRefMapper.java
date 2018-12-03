package com.htxx.mapper;

import com.htxx.entity.FppdRef;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FppdRefMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FppdRef record);

    int insertSelective(FppdRef record);

    FppdRef selectByPrimaryKey(Long id);

    int selectCountByParams(Map<String, Object> map);

    int updateByPrimaryKeySelective(FppdRef record);

    int updateByPrimaryKey(FppdRef record);
}