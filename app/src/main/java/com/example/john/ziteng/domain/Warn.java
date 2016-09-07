package com.example.john.ziteng.domain;

/**
 * 提醒设置初始状态
 * Created by john on 2016/3/28.
 */
public class Warn {
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

    public Warn(boolean success, int obj) {
        this.success = success;
        this.obj = obj;
    }

    private boolean success;
    private int obj;
}
