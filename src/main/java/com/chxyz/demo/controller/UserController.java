package com.chxyz.demo.controller;

import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    /*@Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    public List<UserDO> getUsers() {
        List<UserDO> users=userService.queryAll();
        return users;
    }

    @RequestMapping("/getUserById")
    public UserDO getUserById(Integer id) {
        UserDO user=userService.queryUserById(id);
        return user;
    }

    @RequestMapping("/getUserByName")
    public UserDO getUserByName(String userName){
        UserDO user = userService.queryUserByName(userName);
        return user;
    }*/

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    // 之后需定义并返回一个Result
    public ResponseData login(@RequestParam String username, @RequestParam String password) {
        if ("cecilia726276".equals(username) && "123456".equals(password)) {
            ResponseData responseData = ResponseData.ok();
            UserDO user = new UserDO();
            user.setId(1);
            user.setUserName(username);
            user.setPassword(password);
            responseData.putDataValue("user", user);
            /*String token = JWT.sign(user, 30L * 24L * 3600L * 1000L);
            if (token != null) {
                responseData.putDataValue("token", token);
            }*/
            return responseData;
        }
        return ResponseData.customerError().putDataValue("ERRORS", new String[] { "用户名或者密码错误" });
    }

    /*public void login(@RequestBody Map<String, String> params) {
        log.warn("call AccountOpenServiceImpl accountOpen SUCCESS,RESPONSE:{} ", params);
        //1.验证用户名与密码...
        //2.通过后从数据库中读取该合法user...
        //3.生成token*/
        /*Identity identity = new Identity();
        identity.setId(user.getId());
        identity.setIssuer(issuer);
        identity.setDuration(duration);
        identity.setPhone(user.getUserName());
        identity.setRole(user.getRole());
        String token = Identity.createToken(identity, apiKeySecret);*/
        //4.返回并下发token
    //}

   /* @RequestMapping("/addUser")
    public void save(UserDO user) {
        userService.insertUser(user);
    }

    @RequestMapping(value="/updateUser")
    public void update(UserDO user) {
        userService.updateUser(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }*/

}
