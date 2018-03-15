package com.holdon.user.service;

import com.google.common.base.Strings;
import com.holdon.common.wx.WxSessionCode;
import com.holdon.dao.entity.WxAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;

/**
 * Created by wd on 2018/2/2.
 */
@Service
public class WxAppAuthService {

    private static final String JSCODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type={3}";

    @Autowired
    private WxAppInfoService wxAppInfoService;

    @Autowired
    private WxAccountInfoService wxAccountInfoService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 登录
     *
     * @param jscode
     */
    public void wxLogin(HttpSession session, String jscode) {
        WxSessionCode sessionCode = this.jscode2session(jscode);
        wxAccountInfoService.insert(sessionCode);
        session.setAttribute("loginId", sessionCode.getOpenid());
    }

    /**
     * 解析结果
     *
     * @param jscode
     */
    private WxSessionCode jscode2session(String jscode) {
        String url = this.handleUrl(jscode, wxAppInfoService.findOne());
        if (!Strings.isNullOrEmpty(url)) {
            return restTemplate.getForObject(url, WxSessionCode.class);
        }
        return null;
    }

    /**
     * 组装url
     *
     * @param jscode
     * @param appInfo
     * @return
     */
    private String handleUrl(String jscode, WxAppInfo appInfo) {
        if (null != appInfo) {
            return MessageFormat.format(JSCODE2SESSION, appInfo.getAppid(), appInfo.getSecret(), jscode, "authorization_code");
        }
        return null;
    }
}
