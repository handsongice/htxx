package com.htxx.mapper;

import com.htxx.entity.DlYkfpDel;
import com.htxx.entity.DlYkfpDelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlYkfpDelMapper {
    int countByExample(DlYkfpDelExample example);

    int deleteByExample(DlYkfpDelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlYkfpDel record);

    int insertSelective(DlYkfpDel record);

    List<DlYkfpDel> selectByExample(DlYkfpDelExample example);

    DlYkfpDel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlYkfpDel record, @Param("example") DlYkfpDelExample example);

    int updateByExample(@Param("record") DlYkfpDel record, @Param("example") DlYkfpDelExample example);

    int updateByPrimaryKeySelective(DlYkfpDel record);

    int updateByPrimaryKey(DlYkfpDel record);
}