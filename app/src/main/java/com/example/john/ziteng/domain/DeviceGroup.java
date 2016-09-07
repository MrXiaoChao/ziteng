package com.example.john.ziteng.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 设备群入口站点基本信息
 * Created by john on 2016/3/27.
 */
public class DeviceGroup implements Serializable {

    public DeviceGroup(String deploy_time, String groupId, String groupName, int power, int stored_energy, int voltage, List<EquipListBean> equipList) {
        this.deploy_time = deploy_time;
        this.groupId = groupId;
        this.groupName = groupName;
        this.power = power;
        this.stored_energy = stored_energy;
        this.voltage = voltage;
        this.equipList = equipList;
    }

    private String deploy_time;
    private String groupId;
    private String groupName;
    private int power;
    private int stored_energy;
    private int voltage;


    private List<EquipListBean> equipList;

    public String getDeploy_time() {
        return deploy_time;
    }

    public void setDeploy_time(String deploy_time) {
        this.deploy_time = deploy_time;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public List<EquipListBean> getEquipList() {
        return equipList;
    }

    public void setEquipList(List<EquipListBean> equipList) {
        this.equipList = equipList;
    }

    public static class EquipListBean {

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

        private String equipId;
        private String equip_id;

    }
}
