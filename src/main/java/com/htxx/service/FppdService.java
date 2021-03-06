package com.htxx.service;

import com.htxx.entity.Fppd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface FppdService {

    int deleteByPrimaryKey(Long id);

    int insert(Fppd record);

    int insertSelective(Fppd record);

    Fppd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fppd record);

    int updateByPrimaryKey(Fppd record);

    List<Fppd> selectByParams(Map<String, Object> map);
}