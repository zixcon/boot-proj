package com.holdon.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by wd on 2018/2/5.
 */
@Data
public class WxAccountInfo {

    private Long id;
    private String openid;
    private String unionid;
    private Date createTime;
    private Date updateTime;
}
