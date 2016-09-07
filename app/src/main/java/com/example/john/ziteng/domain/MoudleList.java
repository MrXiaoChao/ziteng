package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 模块列表
 * Created by john on 2016/5/17.
 */
public class MoudleList {
    public MoudleList(String moudleId,String current, String temperature, String voltage, List<BatterylistBean> batterylist) {
        this.moudleId=moudleId;
        this.current = current;
        this.temperature = temperature;
        this.voltage = voltage;
        this.batterylist = batterylist;
    }
    private String moudleId;

    public String getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(String moudleId) {
        this.moudleId = moudleId;
    }
    private String current;
    private String temperature;
    private String voltage;

    private List<BatterylistBean> batterylist;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
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

    public List<BatterylistBean> getBatterylist() {
        return batterylist;
    }

    public void setBatterylist(List<BatterylistBean> batterylist) {
        this.batterylist = batterylist;
    }

    public static class BatterylistBean {
        public BatterylistBean(String batteryId) {
            this.batteryId = batteryId;
        }

        private String batteryId;

        public String getBatteryId() {
            return batteryId;
        }

        public void setBatteryId(String batteryId) {
            this.batteryId = batteryId;
        }
    }
}
