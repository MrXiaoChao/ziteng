package com.example.john.ziteng.domain;

import java.io.Serializable;

/**
 * 地图的经纬度
 * Created by john on 2016/4/12.
 */
public class MarkInfo implements Serializable{

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public MarkInfo(String CITY, String DESCRIPTION, String LATITUDE, String LONGITUDE, String name, String focus, String siteId) {
        this.CITY = CITY;
        this.DESCRIPTION = DESCRIPTION;
        this.LATITUDE = LATITUDE;
        this.LONGITUDE = LONGITUDE;
        this.name = name;
        this.focus = focus;
        this.siteId = siteId;
    }

    private String CITY;
    private String DESCRIPTION;
    private String LATITUDE;//纬度
    private String LONGITUDE;//精度
    private String name;//站点名称
    private String focus;
    private String siteId;

}
