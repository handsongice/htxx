package com.htxx.mapper;

import com.htxx.entity.DlFplx;
import com.htxx.entity.DlFplxExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlFplxMapper {
    int countByExample(DlFplxExample example);

    int deleteByExample(DlFplxExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlFplx record);

    int insertSelective(DlFplx record);

    List<DlFplx> selectByExample(DlFplxExample example);

    DlFplx selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlFplx record, @Param("example") DlFplxExample example);

    int updateByExample(@Param("record") DlFplx record, @Param("example") DlFplxExample example);

    int updateByPrimaryKeySelective(DlFplx record);

    int updateByPrimaryKey(DlFplx record);
}