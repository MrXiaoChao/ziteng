package com.example.john.ziteng.domain;

/**
 * 模块控制
 * Created by john on 2016/4/11.
 */
public class DeviceModer {
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public double getElectricCurren() {
        return electricCurren;
    }

    public void setElectricCurren(double electricCurren) {
        this.electricCurren = electricCurren;
    }

    public DeviceModer(double electricCurren, String moduleId) {
        this.electricCurren = electricCurren;
        this.moduleId = moduleId;
    }



    private String moduleId;
    private double electricCurren;


}
