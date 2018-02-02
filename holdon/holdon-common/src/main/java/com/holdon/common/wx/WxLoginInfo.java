package com.holdon.common.wx;

import lombok.Data;

/**
 * Created by wd on 2018/2/2.
 */
@Data
public class WxLoginInfo {

    private String encryptedData;
    private String errMsg;
    private String iv;
    private String rawData;
    private String signature;
    private WxUserInfo userInfo;
}
