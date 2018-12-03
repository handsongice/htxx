package com.htxx.mapper;

import com.htxx.entity.DlSpxx;
import com.htxx.entity.DlSpxxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlSpxxMapper {
    int countByExample(DlSpxxExample example);

    int deleteByExample(DlSpxxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlSpxx record);

    int insertSelective(DlSpxx record);

    List<DlSpxx> selectByExample(DlSpxxExample example);

    DlSpxx selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlSpxx record, @Param("example") DlSpxxExample example);

    int updateByExample(@Param("record") DlSpxx record, @Param("example") DlSpxxExample example);

    int updateByPrimaryKeySelective(DlSpxx record);

    int updateByPrimaryKey(DlSpxx record);
}