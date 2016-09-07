package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 站点全列表
 * Created by john on 2016/5/4.
 */
public class SiteLists {
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<SiteListInfo> getSiteListInfos() {
        return siteListInfos;
    }

    public void setSiteListInfos(List<SiteListInfo> siteListInfos) {
        this.siteListInfos = siteListInfos;
    }

    public SiteLists(int currentPage, int rows, int totalPage, List<SiteListInfo> siteListInfos) {
        this.currentPage = currentPage;
        this.rows = rows;
        this.totalPage = totalPage;
        this.siteListInfos = siteListInfos;
    }

    private int currentPage;
    private int rows;
    private int totalPage;
    private List<SiteListInfo> siteListInfos;
}
