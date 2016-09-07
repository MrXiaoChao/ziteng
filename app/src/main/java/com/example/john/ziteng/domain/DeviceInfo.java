package com.example.john.ziteng.domain;

/**
 * 设备基本信息
 * Created by john on 2016/5/25.
 */
public class DeviceInfo {
    public String getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(String deploy_time) {
        this.deploy_time = deploy_time;
    }

    public String getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getStored_energy() {
        return stored_energy;
    }

    public void setStored_energy(double stored_energy) {
        this.stored_energy = stored_energy;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public DeviceInfo(String deploy_time, double power, String equip_id, double stored_energy, double voltage) {
        this.deploy_time = deploy_time;
        this.power = power;
        this.equip_id = equip_id;
        this.stored_energy = stored_energy;
        this.voltage = voltage;
    }

    private String deploy_time;
    private String equip_id;
    private double power;
    private double stored_energy;
    private double voltage;
}
