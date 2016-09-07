package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/3/27.
 */
public class ChangPassword {
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public ChangPassword(Boolean success, String msg) {
        this.success = success;
        Msg = msg;
    }

    private Boolean success;
    private String Msg;
}
