package com.chxyz.demo.service.Impl;

import com.chxyz.demo.dao.MessageDOMapper;
import com.chxyz.demo.model.MessageDO;
import com.chxyz.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDOMapper messageDOMapper;

    @Override
    public List<MessageDO> queryAllMessage() {
        return messageDOMapper.queryAllMessage();
    }

    @Override
    public boolean insertMessage(MessageDO messageDO) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        messageDO.setTime(ts);
        try{
            String msg = messageDO.toString();
            log.warn("Try to send message, PARAMETER:{}", msg);
            int effectedNum = messageDOMapper.insert(messageDO);
            if (effectedNum > 0){
                return true;
            }else{
                log.error("Failed to insertUser,PARAMETER:{}", msg);
                throw new RuntimeException("新增信息失败");
            }
        }catch (Exception e){
            log.error("Failed to insert message, PARAMETER:{}", e.getMessage());
            throw new RuntimeException("新增信息失败："+ e.getMessage());
        }
    }
}
