package com.htxx.mapper;

import com.htxx.entity.Enterprise;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnterpriseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);

    List<Enterprise> selectByParams(Map<String, Object> map);

    Enterprise findByParams(Enterprise record);

    int delEnterprise(Long id);
}