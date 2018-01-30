package com.holdon.user.controller;

import com.holdon.common.enums.LoginAuthEnum;
import com.holdon.common.pojo.BaseResult;
import com.holdon.user.service.LoginAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by wd on 2018/1/25.
 */
@Api(value = "登录类",tags = "登录类接口")
@RestController
public class LoginAuthController {

    @Autowired
    private LoginAuthService loginAuthService;

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<Void> login(HttpSession httpSession, String loginName, String loginPass) {
        BaseResult<Void> result = new BaseResult<>();
        String code = loginAuthService.login(loginName, loginPass);
        if (LoginAuthEnum.SUCCESS.getCode().equals(code)) {
            httpSession.setAttribute("loginName", loginName);
        }
        result.setCode(code);
        result.setMessage(LoginAuthEnum.getMsg(code));
        return result;
    }

}
