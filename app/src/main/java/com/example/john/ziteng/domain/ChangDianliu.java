package com.example.john.ziteng.domain;

/**
 * 修改电流
 * Created by john on 2016/4/13.
 */
public class ChangDianliu {
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

    public ChangDianliu(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    private String msg;
    private boolean success;
}
