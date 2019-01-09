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

    @RequestMapping("/getUser")
    public UserDO getUser(Integer id) {
        UserDO user=userService.queryAreaById(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserDO user) {
        userService.insertUser(user);
    }

    @RequestMapping(value="update")
    public void update(UserDO user) {
        userService.updateUser(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }

}
