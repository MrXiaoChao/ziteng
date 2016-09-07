package com.example.john.ziteng.domain;

import java.io.Serializable;

/**
 * 获取地图上的站点详情
 * Created by john on 2016/4/29.
 */
public class MapSiteInfo implements Serializable {
    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getDeploytime() {
        return deploytime;
    }

    public void setDeploytime(String deploytime) {
        this.deploytime = deploytime;
    }

    public String getElectrovalency() {
        return electrovalency;
    }

    public void setElectrovalency(String electrovalency) {
        this.electrovalency = electrovalency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public MapSiteInfo(String deploytime, String CITY, String electrovalency, String id, String name, String storageCapacity) {
        this.deploytime = deploytime;
        this.CITY = CITY;
        this.electrovalency = electrovalency;
        this.id = id;
        this.name = name;
        this.storageCapacity = storageCapacity;
    }

    private String CITY;
    private String deploytime;
    private String electrovalency;
    private String id;
    private String name;
    private String storageCapacity;
}
