package com.chxyz.demo.service.Impl;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
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
        String msg = userId;
        log.warn("Try to queryUserByName,PARAMETER:{}", msg);
        return userMapper.queryUserById(userId);
    }

    @Override
    public UserDO queryUserByName(String userName){
        String msg = userName;
        log.warn("Try to queryUserByName,PARAMETER:{}", msg);
        return userMapper.queryUserByName(userName);
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        if (userDO.getUserName() != null && !"".equals(userDO.getUserName())){
            userDO.setCreateTime(new Date());
            try{
                String msg = userDO.toString();
                log.warn("Try to insertUser,PARAMETER:{}", msg);
                int effectedNum = userMapper.insertUser(userDO);
                if (effectedNum > 0){
                    return true;
                } else {
                    log.error("Failed to insertUser,PARAMETER:{}", msg);
                    throw new RuntimeException("新增用户信息失败");
                }
            }catch (Exception e){
                log.error("Failed to insertUser,PARAMETER:{}", e.getMessage());
                throw new RuntimeException("新增用户信息失败：" + e.getMessage());
            }
        }else{
            log.error("Failed to insertUser,PARAMETER:{}","用户信息为空" );
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
