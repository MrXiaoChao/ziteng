package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 设备群列表里面的设备
 * Created by john on 2016/3/28.
 */
public class DeviceGroupInfo {

    public DeviceGroupInfo(String deploy_time, String equipId, String equip_id, int power, int stored_energy, int voltage, List<UnitlistBean> unitlist) {
        this.deploy_time = deploy_time;
        this.equipId = equipId;
        this.equip_id = equip_id;
        this.power = power;
        this.stored_energy = stored_energy;
        this.voltage = voltage;
        this.unitlist = unitlist;
        this.xinxi=xinxi;
    }
    private String xinxi;
    private String deploy_time;
    private String equipId;
    private String equip_id;
    private int power;
    private int stored_energy;
    private int voltage;

    private List<UnitlistBean> unitlist;
    public String getXinxi(){
        return xinxi;
    }
    public void setXinxi(String xinxi){
        this.xinxi=xinxi;
    }


    public String getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(String deploy_time) {
        this.deploy_time = deploy_time;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getStored_energy() {
        return stored_energy;
    }

    public void setStored_energy(int stored_energy) {
        this.stored_energy = stored_energy;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public List<UnitlistBean> getUnitlist() {
        return unitlist;
    }

    public void setUnitlist(List<UnitlistBean> unitlist) {
        this.unitlist = unitlist;
    }

    public static class UnitlistBean {
        public UnitlistBean(String unitId) {
            this.unitId = unitId;
        }

        private String unitId;

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }
    }
}
