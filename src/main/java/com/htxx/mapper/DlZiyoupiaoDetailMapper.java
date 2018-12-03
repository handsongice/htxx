package com.htxx.mapper;

import com.htxx.entity.DlZiyoupiaoDetail;
import com.htxx.entity.DlZiyoupiaoDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlZiyoupiaoDetailMapper {
    int countByExample(DlZiyoupiaoDetailExample example);

    int deleteByExample(DlZiyoupiaoDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlZiyoupiaoDetail record);

    int insertSelective(DlZiyoupiaoDetail record);

    List<DlZiyoupiaoDetail> selectByExample(DlZiyoupiaoDetailExample example);

    DlZiyoupiaoDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlZiyoupiaoDetail record, @Param("example") DlZiyoupiaoDetailExample example);

    int updateByExample(@Param("record") DlZiyoupiaoDetail record, @Param("example") DlZiyoupiaoDetailExample example);

    int updateByPrimaryKeySelective(DlZiyoupiaoDetail record);

    int updateByPrimaryKey(DlZiyoupiaoDetail record);
}