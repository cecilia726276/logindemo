package com.chxyz.demo.controller;

import com.chxyz.demo.dao.UserMapper;
import com.chxyz.demo.model.UserDO;
import com.chxyz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
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
    }

    @RequestMapping("/addUser")
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
    }

}
