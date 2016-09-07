package com.example.john.ziteng.domain;

/**
 * 修改提醒设置
 * Created by john on 2016/3/28.
 */
public class Warn1 {
    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Warn1(boolean success, int obj) {
        this.success = success;
        this.obj = obj;
    }

    private boolean success;
    private int obj;
}
