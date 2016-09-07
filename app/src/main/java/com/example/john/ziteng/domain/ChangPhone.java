package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/3/26.
 */
public class ChangPhone {
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public ChangPhone(Boolean success, String obj) {
        this.success = success;
        this.obj = obj;
    }

    private Boolean success;
    private String obj;
}
