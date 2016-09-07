package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/3/25.
 */
public class UserLogin {
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserLogin(String level, String msg, boolean success) {
        this.level = level;
        this.msg = msg;
        this.success = success;
    }

    private String level;
    private String msg;
    private boolean success;

}
