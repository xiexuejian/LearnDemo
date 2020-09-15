package com.tky.design_pattern.duty.impl;

import com.tky.design_pattern.duty.AuthInfo;
import com.tky.design_pattern.duty.AuthLink;
import com.tky.design_pattern.duty.AuthService;

import java.util.Date;

public class Level1AuthLink extends AuthLink {

    public Level1AuthLink(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待⼀一级审批负责⼈人 ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼀一级审批完成负责⼈人", " 时间：", f.format(date), " 审批⼈人：", levelUserName);
        }
        return next.doAuth(uId, orderId, authDate);
    }
}
