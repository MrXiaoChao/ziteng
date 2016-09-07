package com.example.john.ziteng.domain;

/**
 * 重要告警信息实体类
 * Created by john on 2016/3/30.
 */
public class WarnInfo {


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }

    public WarnInfo(String time, String siteName, String warnContent) {
        this.time = time;
        this.siteName = siteName;
        this.warnContent = warnContent;
    }

    private String time;
    private String siteName;
    private String warnContent;
}
