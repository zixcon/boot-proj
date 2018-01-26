package com.holdon.user.controller;

import com.holdon.common.pojo.BaseResult;
import com.holdon.user.pojo.AccountInfo;
import com.holdon.user.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by wd on 2018/1/25.
 */
@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResult<Void> login(String loginName, String loginPass) {
        BaseResult<Void> result = new BaseResult<>();
        AccountInfo info = new AccountInfo();
        info.setUserName("sdaf" + new Random().nextInt(100));
        info.setPassword("123456");
        info.setSalt("123456");
        info.setType(0);
        info.setStatus(0);
        userRegisterService.insert(info);
        return result;
    }

    @GetMapping(value = "/test")
    public BaseResult<Void> login1() {
        BaseResult<Void> result = new BaseResult<>();
        AccountInfo info = new AccountInfo();
        info.setUserName("sdaf" + new Random().nextInt(100));
        info.setPassword("123456");
        info.setSalt("123456");
        info.setType(0);
        info.setStatus(0);
        userRegisterService.insert(info);
        return result;
    }

    @GetMapping(value = "/find")
    public BaseResult<AccountInfo> getUserName(String userName) {
        BaseResult<AccountInfo> result = new BaseResult<>();
        AccountInfo info = userRegisterService.findByUserName(userName);
        result.setData(info);
        return result;
    }
}
