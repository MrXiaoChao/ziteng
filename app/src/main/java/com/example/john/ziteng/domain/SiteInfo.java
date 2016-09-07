package com.example.john.ziteng.domain;

import java.io.Serializable;

/**
 * Created by john on 2016/3/23.
 */
public class SiteInfo implements Serializable {


    public int getAllPower() {
        return allPower;
    }

    public void setAllPower(int allPower) {
        this.allPower = allPower;
    }

    public String getAnElectric() {
        return anElectric;
    }

    public void setAnElectric(String anElectric) {
        this.anElectric = anElectric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartPower() {
        return partPower;
    }

    public void setPartPower(int partPower) {
        this.partPower = partPower;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(String storageCapacity) {
        this.storageCapacity = storageCapacity;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SiteInfo(int allPower, String anElectric, String name, int partPower, String site_id, int ups, String storageCapacity, String deploytime, String electrovalency, String city, String url) {
        this.allPower = allPower;
        this.anElectric = anElectric;
        this.name = name;
        this.partPower = partPower;
        this.site_id = site_id;
        this.ups = ups;
        this.storageCapacity = storageCapacity;
        this.deploytime = deploytime;
        this.electrovalency = electrovalency;
        this.city = city;
        this.url = url;
    }

    private int allPower;
    private String anElectric;
    private String name;
    private int partPower;
    private String site_id;
    private int ups;
    private String storageCapacity;
    private String deploytime;
    private String electrovalency;
    private String city;
    private String url;

}
