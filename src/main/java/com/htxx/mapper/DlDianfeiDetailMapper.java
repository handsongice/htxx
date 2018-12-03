package com.htxx.mapper;

import com.htxx.entity.DlDianfeiDetail;
import com.htxx.entity.DlDianfeiDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlDianfeiDetailMapper {
    int countByExample(DlDianfeiDetailExample example);

    int deleteByExample(DlDianfeiDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlDianfeiDetail record);

    int insertSelective(DlDianfeiDetail record);

    int insertSelectiveIntoDetailTemp(DlDianfeiDetail record);

    List<DlDianfeiDetail> selectByExample(DlDianfeiDetailExample example);

    List<DlDianfeiDetail> selectByExampleFromTemp(DlDianfeiDetailExample example);

    DlDianfeiDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlDianfeiDetail record, @Param("example") DlDianfeiDetailExample example);

    int updateByExample(@Param("record") DlDianfeiDetail record, @Param("example") DlDianfeiDetailExample example);

    int updateByPrimaryKeySelective(DlDianfeiDetail record);

    int updateByPrimaryKey(DlDianfeiDetail record);

    void deleteByMainId(@Param("id")long id);
}