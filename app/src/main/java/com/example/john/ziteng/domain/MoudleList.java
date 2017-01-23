package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 模块列表
 * Created by john on 2016/5/17.
 */
public class MoudleList {

    /**
     * batterylist : [{"batteryId":"6000000"},{"batteryId":"6000001"},{"batteryId":"6000002"},{"batteryId":"6000003"},{"batteryId":"6000004"},{"batteryId":"6000005"},{"batteryId":"6000006"},{"batteryId":"6000007"},{"batteryId":"6000008"},{"batteryId":"6000009"},{"batteryId":"6000010"},{"batteryId":"6000011"},{"batteryId":"6000012"},{"batteryId":"6000013"},{"batteryId":"6000014"},{"batteryId":"6000015"}]
     * current : 0
     * moudleid : 5000000
     * status : 空闲
     * temperature : 35
     * voltage : 13702
     */

    private String current;
    private String moudleid;
    private String status;
    private String temperature;
    private String voltage;
    /**
     * batteryId : 6000000
     */

    private List<BatterylistBean> batterylist;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getMoudleid() {
        return moudleid;
    }

    public void setMoudleid(String moudleid) {
        this.moudleid = moudleid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        private String batteryId;

        public String getBatteryId() {
            return batteryId;
        }

        public void setBatteryId(String batteryId) {
            this.batteryId = batteryId;
        }
    }
}
