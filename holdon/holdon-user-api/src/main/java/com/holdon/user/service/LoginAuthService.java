package com.holdon.user.service;

import org.springframework.stereotype.Service;

/**
 * Created by wd on 2018/1/25.
 */
@Service
public class LoginAuthService {


    public String login(String loginName, String loginPass) {
        if ("admin".equals(loginName)) {
            return "0";
        } else {
            return "10000";
        }
    }
}
