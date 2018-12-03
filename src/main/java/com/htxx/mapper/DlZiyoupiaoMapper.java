package com.htxx.mapper;

import com.htxx.entity.DlZiyoupiao;
import com.htxx.entity.DlZiyoupiaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlZiyoupiaoMapper {
    int countByExample(DlZiyoupiaoExample example);

    int deleteByExample(DlZiyoupiaoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlZiyoupiao record);

    int insertSelective(DlZiyoupiao record);

    List<DlZiyoupiao> selectByExample(DlZiyoupiaoExample example);

    DlZiyoupiao selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlZiyoupiao record, @Param("example") DlZiyoupiaoExample example);

    int updateByExample(@Param("record") DlZiyoupiao record, @Param("example") DlZiyoupiaoExample example);

    int updateByPrimaryKeySelective(DlZiyoupiao record);

    int updateByPrimaryKey(DlZiyoupiao record);

    long insertAndGetId(DlZiyoupiao record);
}