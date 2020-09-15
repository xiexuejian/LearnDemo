package com.tky.design_pattern.duty;

import com.alibaba.fastjson.JSON;
import com.tky.design_pattern.duty.impl.Level1AuthLink;
import com.tky.design_pattern.duty.impl.Level2AuthLink;
import com.tky.design_pattern.duty.impl.Level3AuthLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

public class Test_AuthLink {

    private static final Logger logger =LoggerFactory.getLogger(Test_AuthLink .class);

    public static void main(String[] args) throws ParseException {
        AuthLink authLink = new Level3AuthLink("1000013", "王⼯工")
                .appendNext(new Level2AuthLink("1000012", "张经理理")
                        .appendNext(new Level1AuthLink("1000011", "段总")));
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩小傅哥",
                "1000998004813441", new Date())));
// 模拟三级负责⼈人审批
        AuthService.auth("1000013", "1000998004813441");
        logger.info("测试结果：{}", "模拟三级负责⼈人审批，王⼯工");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩小傅哥",
                "1000998004813441", new Date())));
// 模拟⼆二级负责⼈人审批
        AuthService.auth("1000012", "1000998004813441");
        logger.info("测试结果：{}", "模拟⼆二级负责⼈人审批，张经理理");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩小傅哥",
                "1000998004813441", new Date())));
// 模拟⼀一级负责⼈人审批
        AuthService.auth("1000011", "1000998004813441");
        logger.info("测试结果：{}", "模拟⼀一级负责⼈人审批，段总");
        logger.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩小傅哥",
                "1000998004813441", new Date())));
    }
}
