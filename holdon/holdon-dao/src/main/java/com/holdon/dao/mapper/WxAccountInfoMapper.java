package com.holdon.dao.mapper;

import com.holdon.dao.entity.WxAccountInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wd on 2018/2/2.
 */
public interface WxAccountInfoMapper {

    @Select("SELECT * FROM wx_account_info WHERE openid=#{openid} limit 1")
    WxAccountInfo findOne(@Param("openid") String openid);

    @Insert("INSERT INTO wx_account_info(openid, unionid) VALUES(#{openid}, #{unionid})")
    int insert(WxAccountInfo info);
}
