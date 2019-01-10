package com.chxyz.demo.service;

import com.chxyz.demo.model.UserDO;

import java.util.List;

public interface UserService {

    List<UserDO> queryAll();
    UserDO queryUserById(Integer userId);
    UserDO queryUserByName(String userName);
    boolean insertUser(UserDO userDO);
    boolean updateUser(UserDO userDO);
    boolean deleteUser(Integer userId);
}
