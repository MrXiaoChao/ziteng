package com.example.john.ziteng.domain;

/**
 * 站点概况详情
 * Created by john on 2016/4/25.
 */
public class SiteDelicInfo {


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAllPower() {
        return allPower;
    }

    public void setAllPower(String allPower) {
        this.allPower = allPower;
    }

    public String getAllSaveElectricity() {
        return allSaveElectricity;
    }

    public void setAllSaveElectricity(String allSaveElectricity) {
        this.allSaveElectricity = allSaveElectricity;
    }

    public String getAllSaveMoney() {
        return allSaveMoney;
    }

    public void setAllSaveMoney(String allSaveMoney) {
        this.allSaveMoney = allSaveMoney;
    }

    public String getAllemissions() {
        return allemissions;
    }

    public void setAllemissions(String allemissions) {
        this.allemissions = allemissions;
    }

    public String getAveSaveElectricity() {
        return aveSaveElectricity;
    }

    public void setAveSaveElectricity(String aveSaveElectricity) {
        this.aveSaveElectricity = aveSaveElectricity;
    }

    public String getAveSaveMoney() {
        return aveSaveMoney;
    }

    public void setAveSaveMoney(String aveSaveMoney) {
        this.aveSaveMoney = aveSaveMoney;
    }

    public String getAveemissions() {
        return aveemissions;
    }

    public void setAveemissions(String aveemissions) {
        this.aveemissions = aveemissions;
    }

    public String getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(String currentPower) {
        this.currentPower = currentPower;
    }

    public String getCurrentstorageCapacity() {
        return currentstorageCapacity;
    }

    public void setCurrentstorageCapacity(String currentstorageCapacity) {
        this.currentstorageCapacity = currentstorageCapacity;
    }

    public String getPartPower() {
        return partPower;
    }

    public void setPartPower(String partPower) {
        this.partPower = partPower;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public SiteDelicInfo(String city, String cond, String dir, String max, String temperature, String allPower, String allSaveElectricity, String allSaveMoney, String allemissions, String aveSaveElectricity, String aveSaveMoney, String aveemissions, String currentPower, String currentstorageCapacity, String partPower, int status, String statusTime, int ups, String latitude, String longitude) {
        this.city = city;
        this.cond = cond;
        this.dir = dir;
        this.max = max;
        this.temperature = temperature;
        this.allPower = allPower;
        this.allSaveElectricity = allSaveElectricity;
        this.allSaveMoney = allSaveMoney;
        this.allemissions = allemissions;
        this.aveSaveElectricity = aveSaveElectricity;
        this.aveSaveMoney = aveSaveMoney;
        this.aveemissions = aveemissions;
        this.currentPower = currentPower;
        this.currentstorageCapacity = currentstorageCapacity;
        this.partPower = partPower;
        this.status = status;
        this.statusTime = statusTime;
        this.ups = ups;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private String city;
    private String cond;
    private String dir;
    private String max;
    private String temperature;

    private String allPower;
    private String allSaveElectricity;
    private String allSaveMoney;
    private String allemissions;
    private String aveSaveElectricity;
    private String aveSaveMoney;
    private String aveemissions;
    private String currentPower;
    private String currentstorageCapacity;
    private String partPower;
    private int status;
    private String statusTime;
    private int ups;
    private String latitude;
    private String longitude;
}
