package com.holdon.user.service;

import com.holdon.common.wx.WxSessionCode;
import com.holdon.dao.entity.WxAccountInfo;
import com.holdon.dao.mapper.WxAccountInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wd on 2018/2/5.
 */
@Service
public class WxAccountInfoService {

    @Autowired
    private WxAccountInfoMapper wxAccountInfoMapper;

    public WxAccountInfo findOne(String openid) {
        return wxAccountInfoMapper.findOne(openid);
    }

    public void insert(WxSessionCode sessionCode) {
        if (null != sessionCode) {
            WxAccountInfo old = wxAccountInfoMapper.findOne(sessionCode.getOpenid());
            if (null == old) {
                WxAccountInfo info = new WxAccountInfo();
                info.setOpenid(sessionCode.getOpenid());
                info.setUnionid(sessionCode.getUnionid() == null ? "": sessionCode.getUnionid());
                wxAccountInfoMapper.insert(info);
            }
        }
    }
}
