package com.chxyz.demo.service;

import com.chxyz.demo.model.UserDO;

import java.util.List;

public interface UserService {

    List<UserDO> queryAll();
    UserDO queryUserById(String userId);
    UserDO queryUserByName(String userName);
    boolean insertUser(UserDO userDO);
    boolean updateUser(UserDO userDO);
    boolean deleteUser(String userId);
}
