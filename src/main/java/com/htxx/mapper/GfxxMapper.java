package com.htxx.mapper;

import com.htxx.entity.Gfxx;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface GfxxMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gfxx record);

    int insertSelective(Gfxx record);

    Gfxx selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gfxx record);

    int updateByPrimaryKey(Gfxx record);

    List<Gfxx> selectByParams(Map<String, Object> map);

    Gfxx findByParams(Gfxx record);

    int delGfxx(Long id);

    /**
     * 通过关键字查询  （购方名称 购方税号） 字段
     * @param keyword
     * @return
     */
    List<Gfxx> selectByKeyword(@Param("keyword") String keyword);
}