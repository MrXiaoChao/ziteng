package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 设备群列表里面的设备
 * Created by john on 2016/3/28.
 */
public class DeviceGroupInfo {


    /**
     * deploy_time : 2016-05-20 10:16:46
     * electricCurrent : 0.001
     * equipId : 3000000
     * equip_id : 3000000
     * holdTime : 276
     * power : 11
     * state : DISCHARGING
     * stored_energy : 100
     * temperature : 18
     * time : 2016-12-28 17:25:30
     * unitlist : [{"unitId":"4000000"}]
     * voltage : 0.005
     */

    private String deploy_time;
    private double electricCurrent;
    private String equipId;
    private String equip_id;
    private int holdTime;
    private int power;
    private String state;
    private int stored_energy;
    private int temperature;
    private String time;
    private double voltage;
    /**
     * unitId : 4000000
     */

    private List<UnitlistBean> unitlist;

    public String getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(String deploy_time) {
        this.deploy_time = deploy_time;
    }

    public double getElectricCurrent() {
        return electricCurrent;
    }

    public void setElectricCurrent(double electricCurrent) {
        this.electricCurrent = electricCurrent;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public int getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(int holdTime) {
        this.holdTime = holdTime;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStored_energy() {
        return stored_energy;
    }

    public void setStored_energy(int stored_energy) {
        this.stored_energy = stored_energy;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public List<UnitlistBean> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<UnitlistBean> unitlist) {
        this.unitlist = unitlist;
    }

    public static class UnitlistBean {
        private String unitId;

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }
    }
}
