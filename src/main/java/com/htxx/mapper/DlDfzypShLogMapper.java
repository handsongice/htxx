package com.htxx.mapper;

import com.htxx.entity.DlDfzypShLog;
import com.htxx.entity.DlDfzypShLogExample;
import java.util.List;
import java.util.Map;

import com.htxx.entity.DlDianfei;
import com.htxx.entity.PageMsg;
import org.apache.ibatis.annotations.Param;

public interface DlDfzypShLogMapper {
    int countByExample(DlDfzypShLogExample example);

    int deleteByExample(DlDfzypShLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlDfzypShLog record);

    int insertSelective(DlDfzypShLog record);

    List<DlDfzypShLog> selectByExample(DlDfzypShLogExample example);

    DlDfzypShLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlDfzypShLog record, @Param("example") DlDfzypShLogExample example);

    int updateByExample(@Param("record") DlDfzypShLog record, @Param("example") DlDfzypShLogExample example);

    int updateByPrimaryKeySelective(DlDfzypShLog record);

    int updateByPrimaryKey(DlDfzypShLog record);

    List<DlDianfei> getDlDianFeiLogList(Map map);
}