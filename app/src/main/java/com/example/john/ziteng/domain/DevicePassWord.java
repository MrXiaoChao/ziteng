package com.example.john.ziteng.domain;

/**
 * 设备控制密码
 * Created by john on 2016/4/11.
 */
public class DevicePassWord {
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

    public DevicePassWord(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    private String msg;
    private boolean success;
}
