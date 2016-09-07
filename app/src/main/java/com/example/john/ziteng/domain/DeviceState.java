package com.example.john.ziteng.domain;

/**
 * 设备状态
 * Created by john on 2016/5/25.
 */
public class DeviceState {
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DeviceState(String state, String time) {
        this.state = state;
        this.time = time;
    }

    private String state;
    private String time;
}
