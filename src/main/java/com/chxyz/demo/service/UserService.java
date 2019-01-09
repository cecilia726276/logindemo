package com.chxyz.demo.service;

import com.chxyz.demo.model.UserDO;

import java.util.List;

public interface UserService {

    List<UserDO> queryAll();
    UserDO queryAreaById(Integer userId);
    boolean insertUser(UserDO userDO);
    boolean updateUser(UserDO userDO);
    boolean deleteUser(Integer userId);
}
