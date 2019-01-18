package com.chxyz.demo;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.mybatis.mapper.UserDemoDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests implements Serializable {

    @Test
    public void contextLoads() {
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void ttt(){
    System.out.println(userMapper.queryUserById(1L).getUserName());

    }
//    @Autowired
//    UserDemoDOMapper userDemoDOMapper;
//    @Test
//    public void ttt(){
//        System.out.println(userDemoDOMapper.selectByPrimaryKey(1L));
//
//    }
}

