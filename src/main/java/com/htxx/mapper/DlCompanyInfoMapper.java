package com.htxx.mapper;

import com.htxx.entity.DlCompanyInfo;
import com.htxx.entity.DlCompanyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DlCompanyInfoMapper {
    int countByExample(DlCompanyInfoExample example);

    int deleteByExample(DlCompanyInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DlCompanyInfo record);

    int insertSelective(DlCompanyInfo record);

    List<DlCompanyInfo> selectByExample(DlCompanyInfoExample example);

    DlCompanyInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DlCompanyInfo record, @Param("example") DlCompanyInfoExample example);

    int updateByExample(@Param("record") DlCompanyInfo record, @Param("example") DlCompanyInfoExample example);

    int updateByPrimaryKeySelective(DlCompanyInfo record);

    int updateByPrimaryKey(DlCompanyInfo record);
}