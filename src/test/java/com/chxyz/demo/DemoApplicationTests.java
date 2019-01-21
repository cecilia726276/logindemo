package com.chxyz.demo;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
        String uuid = UUID.randomUUID().toString();	//获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        UserDO userDO = new UserDO(uuid, "cecilia01", "726276");
        userDO.setCreateTime(new Date());
        System.out.println(userMapper.insertUser(userDO));
    }
//    @Autowired
//    UserDemoDOMapper userDemoDOMapper;
//    @Test
//    public void ttt(){
//        System.out.println(userDemoDOMapper.selectByPrimaryKey(1L));
//
//    }
}

