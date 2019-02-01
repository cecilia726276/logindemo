package com.chxyz.demo.service;

import com.chxyz.demo.model.MessageDO;


import java.util.List;

public interface MessageService {

    List<MessageDO> queryAllMessage();
    boolean insertMessage(MessageDO messageDO);
}
