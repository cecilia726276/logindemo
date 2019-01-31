package com.chxyz.demo.dao;

import com.chxyz.demo.model.MessageDO;

import java.util.List;

public interface MessageDOMapper {
    List<MessageDO> queryAllMessage();
    //MessageDO queryMessageById(String userId);
    //MessageDO queryUserByName(String userName);

    int insert(MessageDO record);

    int insertSelective(MessageDO record);
}