package com.chxyz.demo.dao;

import com.chxyz.demo.model.UserDO;

import java.io.Serializable;
import java.util.List;


public interface UserMapper  {

    List<UserDO> queryAll();
    UserDO queryUserById(String userId);
    UserDO queryUserByName(String userName);
    int insertUser(UserDO userDO);
    int updateUser(UserDO userDO);
    int deleteUser(String userId);

}
