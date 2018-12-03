package com.htxx.service;

import com.htxx.dto.ResultMap;
import com.htxx.entity.Message;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/10/26 15:01
 */
public interface MessageService {
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Message> getMessageList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param message
     * @return
     */
    ResultMap addMessage(Message message) throws Exception;

    /**
     * 更新数据
     * @param message
     * @return
     */
    ResultMap updateMessage(Message message);

    /**
     * 获取信息
     * @param id
     * @return
     */
    Message getMessageById(Long id);

}
