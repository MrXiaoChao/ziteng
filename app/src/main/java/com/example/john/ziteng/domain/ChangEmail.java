package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/3/27.
 */
public class ChangEmail {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public ChangEmail(boolean success, String obj) {
        this.success = success;
        this.obj = obj;
    }

    private boolean success;
    private String obj;
}
