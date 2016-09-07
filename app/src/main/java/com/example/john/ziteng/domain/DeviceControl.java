package com.example.john.ziteng.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 设备控制
 * Created by john on 2016/4/6.
 */
public class DeviceControl implements Serializable {


    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public List<Unit> getList() {
        return list;
    }

    public void setList(List<Unit> list) {
        this.list = list;
    }

    public DeviceControl(String current, List<Unit> list) {
        this.current = current;
        this.list = list;
    }

    private String current;
    private List<Unit> list;

}
