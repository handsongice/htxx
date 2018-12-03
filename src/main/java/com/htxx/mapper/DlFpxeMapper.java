package com.htxx.mapper;

import com.htxx.entity.DlFpxe;
import com.htxx.entity.DlFpxeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlFpxeMapper {
    int countByExample(DlFpxeExample example);

    int deleteByExample(DlFpxeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlFpxe record);

    int insertSelective(DlFpxe record);

    List<DlFpxe> selectByExample(DlFpxeExample example);

    DlFpxe selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlFpxe record, @Param("example") DlFpxeExample example);

    int updateByExample(@Param("record") DlFpxe record, @Param("example") DlFpxeExample example);

    int updateByPrimaryKeySelective(DlFpxe record);

    int updateByPrimaryKey(DlFpxe record);
}