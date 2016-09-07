package com.example.john.ziteng.domain;

/**
 * 修改密码
 * Created by john on 2016/4/13.
 */
public class PassWord {
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

    public PassWord(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    private String msg;
    private boolean success;
}
