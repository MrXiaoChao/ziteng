package com.example.john.ziteng.domain;

/**
 * 提交意见
 * Created by john on 2016/4/8.
 */
public class Commite {


    public Commite(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    private String msg;
    private boolean success;

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
}
