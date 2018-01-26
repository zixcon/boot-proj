package com.holdon.user.service;

import com.holdon.user.mapper.AccountInfoMapper;
import com.holdon.user.pojo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wd on 2018/1/25.
 */
@Service
public class UserRegisterService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;


    public void insert(AccountInfo info) {
        accountInfoMapper.insert(info);
    }

    public AccountInfo findByUserName(String userName) {
        List<AccountInfo> list = accountInfoMapper.findByName(userName);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
