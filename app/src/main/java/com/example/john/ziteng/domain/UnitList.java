package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 单元列表
 * Created by john on 2016/5/17.
 */
public class UnitList {
    public UnitList(String current, int status, String voltage, String unitId, List<ModulelistBean> modulelist) {
        this.current = current;
        this.status = status;
        this.voltage = voltage;
        this.unitId = unitId;
        this.modulelist = modulelist;
    }

    private String current;
    private int status;
    private String unitId;
    private String voltage;

    private List<ModulelistBean> modulelist;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public List<ModulelistBean> getModulelist() {
        return modulelist;
    }

    public void setModulelist(List<ModulelistBean> modulelist) {
        this.modulelist = modulelist;
    }

    public static class ModulelistBean {
        public ModulelistBean(String moudleId) {
            this.moudleId = moudleId;
        }

        private String moudleId;

        public String getMoudleId() {
            return moudleId;
        }

        public void setMoudleId(String moudleId) {
            this.moudleId = moudleId;
        }
    }
}
