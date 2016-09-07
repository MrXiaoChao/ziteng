package com.example.john.ziteng.domain;

/**
 * 城市PID
 * Created by john on 2016/4/28.
 */
public class CityPid {
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public CityPid(int pId, String pName) {
        this.pId = pId;
        this.pName = pName;
    }

    private int pId;
    private String pName;
}
