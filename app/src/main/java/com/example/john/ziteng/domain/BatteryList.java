package com.example.john.ziteng.domain;

/**
 *  电池列表
 * Created by john on 2016/5/17.
 */
public class BatteryList {
    public BatteryList(String batteryid, String current, String soc, String state, String soh, String temperature, String voltage) {
        this.batteryid = batteryid;
        this.current = current;
        this.soc = soc;
        this.state = state;
        this.soh = soh;
        this.temperature = temperature;
        this.voltage = voltage;
    }

    private String batteryid;
    private String current;
    private String soc;
    private String soh;
    private String state;
    private String temperature;
    private String voltage;

    public String getBatteryid() {
        return batteryid;
    }

    public void setBatteryid(String batteryid) {
        this.batteryid = batteryid;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getSoh() {
        return soh;
    }

    public void setSoh(String soh) {
        this.soh = soh;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }
}
