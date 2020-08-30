package com.tky.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("login")
    public String login(String name, Integer type) throws InterruptedException {
        return "访问成功";
    }

    @GetMapping("log")
    public String log(){
        return "日志打印";
    }
}
