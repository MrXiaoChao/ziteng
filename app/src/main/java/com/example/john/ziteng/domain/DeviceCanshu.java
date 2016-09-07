package com.example.john.ziteng.domain;

/**
 * 设备基本参数
 * Created by john on 2016/5/25.
 */
public class DeviceCanshu {


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

    public double getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(double holdTime) {
        this.holdTime = holdTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public DeviceCanshu(double electricCurrent, String equipId, double holdTime, double temperature, double voltage) {
        this.electricCurrent = electricCurrent;
        this.equipId = equipId;
        this.holdTime = holdTime;
        this.temperature = temperature;
        this.voltage = voltage;
    }

    private double electricCurrent;
    private String equipId;
    private double holdTime;
    private double temperature;
    private double voltage;
}
