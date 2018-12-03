package com.htxx.mapper;

import com.htxx.entity.Fpsg;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FpsgMapper {

    int deleteByPrimaryKey(Fpsg key);

    int insert(Fpsg record);

    int insertSelective(Fpsg record);

    Fpsg selectByPrimaryKey(Fpsg key);

    int updateByPrimaryKeySelective(Fpsg record);

    int updateByPrimaryKey(Fpsg record);

    int updateCheck(Fpsg record);

    List<Fpsg> selectByParams(Map<String, Object> map);
}