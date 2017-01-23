package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 单元列表
 * Created by john on 2016/5/17.
 */
public class UnitList {

    /**
     * current : 0
     * modulelist : [{"moudleId":"5000000"},{"moudleId":"5000001"},{"moudleId":"5000002"},{"moudleId":"5000003"}]
     * status : 空闲
     * unitId : 4000000
     * voltage : 13702
     */

    private String current;
    private String status;
    private String unitId;
    private String voltage;
    /**
     * moudleId : 5000000
     */

    private List<ModulelistBean> modulelist;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        private String moudleId;

        public String getMoudleId() {
            return moudleId;
        }

        public void setMoudleId(String moudleId) {
            this.moudleId = moudleId;
        }
    }
}
