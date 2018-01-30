package com.holdon.user.controller;

import com.holdon.common.pojo.BaseResult;
import com.holdon.dao.entity.AccountInfo;
import com.holdon.user.service.UserRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * Created by wd on 2018/1/25.
 */
@Api(value = "用户类",tags = "用户类接口")
@RestController
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResult<Void> login(@ApiParam(value = "用户",required = true)String loginName,
                                  @ApiParam("密码")String loginPass) {
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

    @PostMapping(value = "/find")
//    @ApiModel("实体类")
    public BaseResult<AccountInfo> getUser(@RequestBody AccountInfo info) {
        BaseResult<AccountInfo> result = new BaseResult<>();
        result.setData(info);
        return result;
    }
}
