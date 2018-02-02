package com.holdon.user.service;

import com.holdon.dao.entity.WxAppInfo;
import com.holdon.dao.mapper.WxAppInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2018/2/2.
 */
@Service
public class WxAppAuthService {

    @Autowired
    private WxAppInfoMapper wxAppInfoMapper;

    public WxAppInfo getHoldOnAppInfo() {
        return wxAppInfoMapper.findOne(0);
    }
}
