package com.example.john.ziteng.domain;

/**
 * Created by john on 2016/12/27.
 */
public class Shebeigaojing{

    public Shebeigaojing(String time, String warnContent) {
        this.time = time;
        this.warnContent = warnContent;
    }

    /**
     * time :  201 6-12-22 15:13:31
     * warnContent : 电池故障
     */


    private String time;
    private String warnContent;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }
}
