package com.htxx.mapper;

import com.htxx.entity.DlDianfei;
import com.htxx.entity.DlDianfeiExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DlDianfeiMapper {
    int countByExample(DlDianfeiExample example);

    int deleteByExample(DlDianfeiExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlDianfei record);

    int insertSelective(DlDianfei record);

    List<DlDianfei> selectByExample(DlDianfeiExample example);

    List<DlDianfei> selectFromDlDianfeiTemp(DlDianfeiExample example);

    DlDianfei selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlDianfei record, @Param("example") DlDianfeiExample example);

    int updateByExample(@Param("record") DlDianfei record, @Param("example") DlDianfeiExample example);

    int updateByPrimaryKeySelective(DlDianfei record);

    int updateByPrimaryKey(DlDianfei record);

    //根据条件查询数量
    int countByMap(Map map);
    //根据条件删除
    int deleteByMap(Map map);
    //根据条件查询
    List<DlDianfei> selectByCondition(Map map);

    long insertSelectiveAndGetId(DlDianfei dlDianfei);
    long insertSelectiveInToTempAndGetId(DlDianfei dlDianfei);

    List<DlDianfei> getDlDianfeiListById(String[] id);

    List<DlDianfei> selectByMap(Map<String, String> paramMap);
}