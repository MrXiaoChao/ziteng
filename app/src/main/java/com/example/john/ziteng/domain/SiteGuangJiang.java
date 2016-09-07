package com.example.john.ziteng.domain;

/**
 * 站点关键数据
 * Created by john on 2016/4/25.
 */
public class SiteGuangJiang {
    public String getAllUps() {
        return allUps;
    }

    public void setAllUps(String allUps) {
        this.allUps = allUps;
    }

    public String getDayOutpowerNum() {
        return dayOutpowerNum;
    }

    public void setDayOutpowerNum(String dayOutpowerNum) {
        this.dayOutpowerNum = dayOutpowerNum;
    }

    public String getDayUps() {
        return dayUps;
    }

    public void setDayUps(String dayUps) {
        this.dayUps = dayUps;
    }

    public String getDayOutpowerOfen() {
        return dayOutpowerOfen;
    }

    public void setDayOutpowerOfen(String dayOutpowerOfen) {
        this.dayOutpowerOfen = dayOutpowerOfen;
    }

    public String getMonOutpowerNum() {
        return monOutpowerNum;
    }

    public void setMonOutpowerNum(String monOutpowerNum) {
        this.monOutpowerNum = monOutpowerNum;
    }

    public String getMonOutpowerOfen() {
        return monOutpowerOfen;
    }

    public void setMonOutpowerOfen(String monOutpowerOfen) {
        this.monOutpowerOfen = monOutpowerOfen;
    }

    public String getMonUps() {
        return monUps;
    }

    public void setMonUps(String monUps) {
        this.monUps = monUps;
    }

    public String getYearOutpowerNum() {
        return yearOutpowerNum;
    }

    public void setYearOutpowerNum(String yearOutpowerNum) {
        this.yearOutpowerNum = yearOutpowerNum;
    }

    public String getYearOutpowerOfen() {
        return yearOutpowerOfen;
    }

    public void setYearOutpowerOfen(String yearOutpowerOfen) {
        this.yearOutpowerOfen = yearOutpowerOfen;
    }

    public String getYearUps() {
        return yearUps;
    }

    public void setYearUps(String yearUps) {
        this.yearUps = yearUps;
    }

    public SiteGuangJiang(String allUps, String dayOutpowerNum, String dayOutpowerOfen, String dayUps, String monOutpowerNum, String monOutpowerOfen, String monUps, String yearOutpowerNum, String yearOutpowerOfen, String yearUps) {
        this.allUps = allUps;
        this.dayOutpowerNum = dayOutpowerNum;
        this.dayOutpowerOfen = dayOutpowerOfen;
        this.dayUps = dayUps;
        this.monOutpowerNum = monOutpowerNum;
        this.monOutpowerOfen = monOutpowerOfen;
        this.monUps = monUps;
        this.yearOutpowerNum = yearOutpowerNum;
        this.yearOutpowerOfen = yearOutpowerOfen;
        this.yearUps = yearUps;
    }

    private String allUps;
    private String dayOutpowerNum;
    private String dayOutpowerOfen;
    private String dayUps;
    private String monOutpowerNum;
    private String monOutpowerOfen;
    private String monUps;
    private String yearOutpowerNum;
    private String yearOutpowerOfen;
    private String yearUps;
}
