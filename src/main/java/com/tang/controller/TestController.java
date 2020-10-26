package com.tang.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    public String adminTest(){
        return "admin测试成功";
    }

    @GetMapping("/user")
    public String userTest(){
        return "user测试成功";
    }

    @GetMapping("/guest")
    public String guestTest(){
        return "guest测试成功";
    }


}
