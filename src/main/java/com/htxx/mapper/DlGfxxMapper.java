package com.htxx.mapper;

import com.htxx.entity.DlGfxx;
import com.htxx.entity.DlGfxxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlGfxxMapper {
    int countByExample(DlGfxxExample example);

    int deleteByExample(DlGfxxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlGfxx record);

    int insertSelective(DlGfxx record);

    List<DlGfxx> selectByExample(DlGfxxExample example);

    DlGfxx selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlGfxx record, @Param("example") DlGfxxExample example);

    int updateByExample(@Param("record") DlGfxx record, @Param("example") DlGfxxExample example);

    int updateByPrimaryKeySelective(DlGfxx record);

    int updateByPrimaryKey(DlGfxx record);

    List<DlGfxx> selectByCondition(DlGfxx dlGfxx);

    void batchDelete(String[] id);

    List<DlGfxx> get10GfInfo(DlGfxx dlGfxx);
}