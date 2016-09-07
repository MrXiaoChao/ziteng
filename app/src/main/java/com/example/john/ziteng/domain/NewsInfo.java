package com.example.john.ziteng.domain;

/**
 * 资讯
 * Created by john on 2016/3/24.
 */
public class NewsInfo {
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }


    public NewsInfo(String picUrl, String titile, String newId, String date, String descrip) {
        this.picUrl = picUrl;
        this.titile = titile;
        this.newId = newId;
        this.date = date;
        this.descrip = descrip;
    }

    private String picUrl;
    private String titile;
    private String newId;
    private String date;
    private String descrip;

}
