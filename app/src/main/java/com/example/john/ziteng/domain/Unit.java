package com.example.john.ziteng.domain;

import java.io.Serializable;

/**
 * 单元控制
 * Created by john on 2016/4/6.
 */
public class Unit implements Serializable {


    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Unit(String unitId) {
        this.unitId = unitId;
    }

    private String unitId;

}
