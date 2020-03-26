package com.tg.oauth.server.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal getUser(Principal user){
        log.info(JSON.toJSONString(user));
        return user;
    }

    @GetMapping("/test/test1")
    public  String test(){
        return "匿名访问 ok";
    }
}
