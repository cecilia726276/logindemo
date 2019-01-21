package com.chxyz.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class UserDO {

    private static final long serialVersionUID = -606293710771447475L;
    // 用户名 不可为null
    private String userName;

    // 用户id 不可为null 唯一
    private String id;

    // 密码 不可为null
    private String password;

    // 账户创建时间 不可为null
    private Date createTime;

    // 出生日期 可为null
    private Date dob;

    // 性别 可为null
    private Boolean sex;

    public UserDO(){
        super();
    }

    public UserDO(String id, String userName, String password ){
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

}
