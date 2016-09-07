package com.example.john.ziteng.domain;

/**
 * 全网监控
 * Created by john on 2016/3/26.
 */
public class Monitor {
    public String getCurrentstorage() {
        return currentstorage;
    }

    public void setCurrentstorage(String currentstorage) {
        this.currentstorage = currentstorage;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public Monitor(String currentstorage, String num, String storageCapacity) {
        this.currentstorage = currentstorage;
        this.num = num;
        this.storageCapacity = storageCapacity;
    }

    private String currentstorage;
    private String num;
    private String storageCapacity;
}
