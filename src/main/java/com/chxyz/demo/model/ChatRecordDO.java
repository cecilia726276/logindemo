package com.chxyz.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class ChatRecordDO {

    // 聊天内容
    String content;

    // 创建时间
    Date createTime;

    // 聊天成员
    String userId;
}
