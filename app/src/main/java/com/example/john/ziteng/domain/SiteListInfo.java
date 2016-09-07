package com.example.john.ziteng.domain;

/**
 * 站点全列表
 * Created by john on 2016/5/4.
 */
public class SiteListInfo {
    public SiteListInfo(String manage, String s_PROVNAME, String companyName, String focus, String kind, String name, String siteId, String status, String url) {
        this.manage = manage;
        this.S_PROVNAME = s_PROVNAME;
        this.companyName = companyName;
        this.focus = focus;
        this.kind = kind;
        this.name = name;
        this.siteId = siteId;
        this.status = status;
        this.url = url;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getS_PROVNAME() {
        return S_PROVNAME;
    }

    public void setS_PROVNAME(String s_PROVNAME) {
        S_PROVNAME = s_PROVNAME;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String manage;
    private String S_PROVNAME;
    private String companyName;
    private String focus;
    private String kind;
    private String name;
    private String siteId;
    private String status;
    private String url;
}
