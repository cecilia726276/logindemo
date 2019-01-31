package com.chxyz.demo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chxyz.demo.controller.UserController;
import com.chxyz.demo.dao.MessageDOMapper;
import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin2.message.Message;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests implements Serializable {

    @Test
    public void contextLoads() {
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    MessageDOMapper messageDOMapper;

    @Test
    public void ttt(){
        System.out.println(messageDOMapper.queryAllMessage());
    }

//    @Test
//    public void ttt() throws UnsupportedEncodingException {
//        //String uuid = UUID.randomUUID().toString();	//获取UUID并转化为String对象
//        //uuid = uuid.replace("-", "");
//        String uuid= "ebc4292c7904497e90f69c5b03499b9f";
//        UserDO userDO = new UserDO(uuid, "cecilia07", "726276");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("alg", "HS256"); // 声明加密的算法 通常直接使用 HMAC SHA256
//        map.put("typ", "JWT");
//        long maxAge = 30L * 24L * 3600L * 1000L;
//        map.put("exp", System.currentTimeMillis() + maxAge);
//        String token = JWT.create()
//                .withHeader(map)
//                .withClaim("id", uuid)
//                .sign(Algorithm.HMAC256("secret"));
//        userDO.setLatestToken(token);
//        System.out.println(userMapper.updateUser(userDO));
//    }
//    @Autowired
//    UserDemoDOMapper userDemoDOMapper;
//    @Test
//    public void ttt(){
//        System.out.println(userDemoDOMapper.selectByPrimaryKey(1L));
//
//    }
}

