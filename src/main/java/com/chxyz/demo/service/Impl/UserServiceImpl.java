package com.chxyz.demo.service.Impl;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDO> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public UserDO queryAreaById(Integer userId) {
        return userMapper.queryAreaById(userId);
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return false;
    }
}
