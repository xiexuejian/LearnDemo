package com.tky.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("login")
    @SentinelResource(value="sentinelLogin", blockHandler = "blockHandler", fallback =
            "fallbackHandler")
    public String login(String name, Integer type) throws InterruptedException {
        if("xxj".equals(name)){
            System.out.println(1/0);
        }
        return "访问成功";
    }

    @GetMapping("log")
    public String log(){
        return "日志打印";
    }

    //排队等待
    @GetMapping("rpcInvoke")
    public String RpcInvoke() {
        final AtomicInteger ato = new AtomicInteger(0);
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                String res = restTemplate.getForObject("http://localhost:8086/login", String.class);
                System.out.println("线程"+ato.incrementAndGet()+"------------"+res);
            }).start();

        }
        return "invoke-success";
    }

    /**
     *
     *  流控触发后的降级方法：热点规则
     *
     * */
    public String blockHandler(String name,  Integer type, BlockException e) {
        System.out.println("--------------------blockHandler------------------");
        return "blockHandler";
    }

    /**
     *  异常后，触发后的降级方法
     * */
    public String fallbackHandler(String  name,  Integer type){
        System.out.println("--------------------fallbackHandler-------------------");
        return "fallbackHandler";
    }
}
