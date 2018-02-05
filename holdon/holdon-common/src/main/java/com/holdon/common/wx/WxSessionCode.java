package com.holdon.common.wx;

import lombok.Data;

/**
 * Created by wd on 2018/2/5.
 */
@Data
public class WxSessionCode {
    //正常返回的JSON数据包
    private String openid;
    private String session_key;
    private String unionid;
    //错误时返回JSON数据包(示例为Code无效)
    private String errcode;
    private String errmsg;

}
