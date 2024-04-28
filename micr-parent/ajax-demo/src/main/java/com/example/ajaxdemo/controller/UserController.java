package com.example.ajaxdemo.controller;

import com.example.ajaxdemo.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//support cross-field
@CrossOrigin
@RestController
public class UserController {
    @GetMapping("/user/query")
    public User queryUser() {
        System.out.println("接受前端请求/user/query");
        User user = new User(1001, "zhangqiang", 20, "male");
        return user;
    }

    @GetMapping("/user/get")
    public User queryUser2(Integer id, String name) {
        System.out.println("接受前端请求/user/query");
        User user = new User(1001, "zhangqiang", 20, "male");
        return user;
    }


}
