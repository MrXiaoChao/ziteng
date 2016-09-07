package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/3/24.
 */
public class Pic {
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public Pic(String picUrl, String titile, String newId) {
        this.picUrl = picUrl;
        this.titile = titile;
        this.newId = newId;
    }

    private String picUrl;
    private String titile;
    private String newId;
}
