package com.example.john.ziteng.domain;

/**
 * 模块控制
 * Created by john on 2016/4/6.
 */
public class Mondlues {
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public double getElectricCurrent() {
        return electricCurrent;
    }

    public void setElectricCurrent(double electricCurrent) {
        this.electricCurrent = electricCurrent;
    }

    public Mondlues(String moduleId, double electricCurrent) {
        this.moduleId = moduleId;
        this.electricCurrent = electricCurrent;
    }

    private String moduleId;
    private double electricCurrent;
}
