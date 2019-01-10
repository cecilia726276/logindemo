package com.chxyz.demo.dao;

import com.chxyz.demo.model.UserDO;

import java.util.List;

public interface UserMapper {

    List<UserDO> queryAll();
    UserDO queryUserById(Integer userId);
    UserDO queryUserByName(String userName);
    int insertUser(UserDO userDO);
    int updateUser(UserDO userDO);
    int deleteUser(Integer userId);

}
