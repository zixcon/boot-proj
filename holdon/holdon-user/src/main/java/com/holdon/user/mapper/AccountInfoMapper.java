package com.holdon.user.mapper;

import com.holdon.user.pojo.AccountInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by wd on 2018/1/25.
 */
@Mapper
public interface AccountInfoMapper {

    @Results({
            @Result(property = "userName", column = "user_name"),
            @Result(property = "createTime", column = "create_Time"),
            @Result(property = "updateTime", column = "update_Time")
    })
    @Select("SELECT * FROM account_info WHERE user_name = #{userName}")
    List<AccountInfo> findByName(@Param("userName") String userName);

    @Select("SELECT * FROM account_info WHERE id = #{id}")
    List<AccountInfo> findById(@Param("id") int id);

    @Insert("INSERT INTO account_info(user_name, password, salt, type, status) VALUES(#{userName}, #{password}, #{salt}, #{type},  #{status} )")
    int insert(AccountInfo info);

    // 两个语句实现效果一致
    // @Insert("INSERT INTO user_info(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR}, #{usertype,jdbcType=VARCHAR},#{enabled,jdbcType=INTEGER}, #{realname,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR})")
    @Insert("INSERT INTO account_info(id,user_name, password) VALUES(#{id}, #{userName}, #{password})")
    int insertByMap(Map<String, Object> map);

    @Select("SELECT * FROM account_info WHERE 1=1 ")
    List<AccountInfo> findAll();

    @Update("UPDATE account_info SET password=#{password},salt=#{salt} WHERE username=#{userName}")
    void updatePassword(AccountInfo info);

    @Delete("DELETE FROM account_info WHERE id =#{id}")
    void delete(int id);

    @Results({@Result(property = "user_name", column = "create_time"), @Result(property = "userName", column = "createTime")})
    @Select("SELECT login_name , type, status,create_time, update_time FROM account_info WHERE id=#{id}")
    List<AccountInfo> queryById(@Param("id") int id);
}
