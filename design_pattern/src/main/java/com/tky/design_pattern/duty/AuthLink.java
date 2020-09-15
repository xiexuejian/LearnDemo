package com.tky.design_pattern.duty;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AuthLink {

    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式化
    protected String levelUserId; // 级别⼈人员ID
    protected String levelUserName; // 级别⼈人员姓名
    private AuthLink next; // 责任链
    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink next() {
        return next;
    }

    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }
    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
