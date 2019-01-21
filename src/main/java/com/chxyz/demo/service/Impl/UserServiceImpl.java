package com.chxyz.demo.service.Impl;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //@Autowired
    //private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<UserDO> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public UserDO queryUserById(String userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public UserDO queryUserByName(String userName){
        return userMapper.queryUserByName(userName);
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        if (userDO.getUserName() != null && !"".equals(userDO.getUserName())){
            userDO.setCreateTime(new Date());
            try{
                int effectedNum = userMapper.insertUser(userDO);
                if (effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("新增用户信息失败");
                }
            }catch (Exception e){
                throw new RuntimeException("新增用户信息失败：" + e.getMessage());
            }
        }else{
            throw new RuntimeException("用户信息不能为空！");
        }
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

    /*public void setSessionTemplate(SqlSessionTemplate sessionTemplate){

    }*/
}
