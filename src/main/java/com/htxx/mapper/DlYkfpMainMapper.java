package com.htxx.mapper;

import com.htxx.entity.DlYkfpMain;
import com.htxx.entity.DlYkfpMainExample;
import java.util.List;

import com.htxx.entity.DlYkfpMainPage;
import org.apache.ibatis.annotations.Param;

public interface DlYkfpMainMapper {
    int countByExample(DlYkfpMainExample example);

    int deleteByExample(DlYkfpMainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlYkfpMain record);

    int insertSelective(DlYkfpMain record);

    List<DlYkfpMain> selectByExample(DlYkfpMainExample example);

    DlYkfpMain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlYkfpMain record, @Param("example") DlYkfpMainExample example);

    int updateByExample(@Param("record") DlYkfpMain record, @Param("example") DlYkfpMainExample example);

    int updateByPrimaryKeySelective(DlYkfpMain record);

    int updateByPrimaryKey(DlYkfpMain record);

    DlYkfpMainPage getDlYkfpMainPage(@Param("djh") String djh);
}