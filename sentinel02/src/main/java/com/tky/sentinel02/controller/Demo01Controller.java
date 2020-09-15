package com.tky.sentinel02.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo01Controller {

    @GetMapping("/getInfo")
    @SentinelResource(value = "getInfo")
    public String getInfo(){
        return "获取数据成功";
    }
}
