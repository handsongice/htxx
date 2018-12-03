package com.htxx.service.impl;

import com.htxx.constent.Constent;
import com.htxx.dto.ResultMap;
import com.htxx.entity.Message;
import com.htxx.mapper.MessageMapper;
import com.htxx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/26 12:57
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessageList(Map<String, Object> params) throws Exception {
        List<Message> messages = messageMapper.selectByParams(params);
        return messages;
    }

    @Override
    public ResultMap addMessage(Message message) throws Exception {
        return null;
    }

    @Override
    public ResultMap updateMessage(Message message) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置创建时间
        message.setUpdateTime(df.format(new Date()));
        int dbResult = messageMapper.updateByPrimaryKeySelective(message);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_UPDATE_FAILURE);
        }
        return ResultMap.success(Constent.DB_UPDATE_SUCCESS);
    }

    @Override
    public Message getMessageById(Long id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        return message;
    }
}
