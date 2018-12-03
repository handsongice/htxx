package com.htxx.mapper;

import com.htxx.entity.Ykfp;
import com.htxx.entity.YkfpFpjx;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface YkfpFpjxMapper {

    int deleteByPrimaryKey(Long id);

    int insert(YkfpFpjx record);

    int insertSelective(YkfpFpjx record);

    YkfpFpjx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YkfpFpjx record);

    int selectCountByFpId(Long fpId);

    int updateByPrimaryKey(YkfpFpjx record);

    List<Ykfp> selectListByParams(Map<String, Object> map);
}