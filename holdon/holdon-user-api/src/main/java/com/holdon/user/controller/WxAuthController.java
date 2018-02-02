package com.holdon.user.controller;

import com.holdon.common.pojo.BaseResult;
import com.holdon.common.wx.WxLoginInfo;
import com.holdon.dao.entity.WxAppInfo;
import com.holdon.user.service.WxAppAuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * {errMsg: "login:ok", code: "013P3cKw123Kda06KGKw18LrKw1P3cKy"}
 * {errMsg: "getUserInfo:ok", rawData: "{"nickName":"li5ly0","gender":1,"language":"zh_CN"…P2ohLrJWgPR6TeSu2ssghN0yOK5yqSYEEy9uKvMEgqrEg/0"}", userInfo: {…}, signature: "d75014a1b2e36303e2b6877ca53e9af97a05dbd0", encryptedData: "g803zuWBa7hh3KWwJZd8M6YpgWi1bRvF51j48ds8I2GfvZc2ph…xS52Vdbaj7MwtjFcaUL8mQNx2Sos2BcD7rX1ZxhwRegh5PCU=", …}encryptedData: "g803zuWBa7hh3KWwJZd8M6YpgWi1bRvF51j48ds8I2GfvZc2phB4RBZoodec/hwx6uy3bc/DJTMhuKfSF+kLmxKST86l67kmcZDYN3z/9Y6fH1N7qXRYlZyit0fsZCrJVazmAw9Lfsl9bOPNnCd490uwg/TCHN2KtpI7o5FaNBQMokXUpUdSeq2GUmspAY6+QMfu+d+CLk249DuvyMMdVC6QWiV9+zuC2rpMKzMKaA+zZ55LeWjSuq3OOPZTGtfuL+85a9UZtMDzQncu1ng1XFx3rOAMOnED3HNAWyCpl8AIcKO4cRpb/davmxAZOS7N1nGU2ip8NfXz1ztKDwtIoeR77LYQ0in/8TnuX/N4OL9RpUo14sqMRurG9e/NiIVxjyNCTkMxxqELvUDvBlIN+bbNasoZ66vgvdzjwCjX95ua7m0NrcXGms9lXqBxS52Vdbaj7MwtjFcaUL8mQNx2Sos2BcD7rX1ZxhwRegh5PCU="errMsg: "getUserInfo:ok"iv: "gJraf/chApFDK7GIa8zHTQ=="rawData: "{"nickName":"li5ly0","gender":1,"language":"zh_CN","city":"Hangzhou","province":"Zhejiang","country":"China","avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/amDQcvTNcT1L3UXfhzIAzia7ias3dEhIvQYYeHbdxwxP2ohLrJWgPR6TeSu2ssghN0yOK5yqSYEEy9uKvMEgqrEg/0"}"signature: "d75014a1b2e36303e2b6877ca53e9af97a05dbd0"userInfo: {nickName: "li5ly0", gender: 1, language: "zh_CN", city: "Hangzhou", province: "Zhejiang", …}__proto__: Object
 * Created by wd on 2018/1/31.
 */
@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {

    @Autowired
    private WxAppAuthService wxAppAuthService;

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<WxLoginInfo> login(@RequestBody WxLoginInfo info) {
        BaseResult<WxLoginInfo> result = new BaseResult<>();
        result.setData(info);
        return result;
    }

    @ApiOperation(value = "获取appid", notes = "获取appid")
    @RequestMapping(value = "/appid", method = RequestMethod.POST)
    public BaseResult<WxAppInfo> getAppId() {
        BaseResult<WxAppInfo> result = new BaseResult<>();
        WxAppInfo wxAppInfo = wxAppAuthService.getHoldOnAppInfo();
        result.setData(wxAppInfo);
        return result;
    }
}
