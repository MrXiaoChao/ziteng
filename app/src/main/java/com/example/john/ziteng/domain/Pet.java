package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/5/30.
 */
public class Pet {
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Pet(String time, String content, String models, String sys) {
        this.time = time;
        this.content = content;
        this.models = models;
        this.sys = sys;
    }

    private String time;
    private String content;
    private String models;
    private String sys;
}
