package com.htxx.mapper;

import com.htxx.entity.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Component
public interface MessageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> selectByParams(Map<String, Object> map);
}