package com.holdon.common.enums;

/**
 * Created by wd on 2018/1/25.
 */
public enum LoginAuthEnum {
    SUCCESS("0", "登录成功"),
    FAIL("10000", "用户名或密码错误");


    private String code;
    private String msg;


    LoginAuthEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(String code) {
        for (LoginAuthEnum authEnum : LoginAuthEnum.values()) {
            if (authEnum.getCode().equals(code)) {
                return authEnum.getMsg();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
