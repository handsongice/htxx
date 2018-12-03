package com.htxx.mapper;

import com.htxx.entity.DlDfzypShl;
import com.htxx.entity.DlDfzypShlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlDfzypShlMapper {
    int countByExample(DlDfzypShlExample example);

    int deleteByExample(DlDfzypShlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlDfzypShl record);

    int insertSelective(DlDfzypShl record);

    List<DlDfzypShl> selectByExample(DlDfzypShlExample example);

    DlDfzypShl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlDfzypShl record, @Param("example") DlDfzypShlExample example);

    int updateByExample(@Param("record") DlDfzypShl record, @Param("example") DlDfzypShlExample example);

    int updateByPrimaryKeySelective(DlDfzypShl record);

    int updateByPrimaryKey(DlDfzypShl record);
}