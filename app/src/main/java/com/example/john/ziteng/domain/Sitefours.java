package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 关注站点
 * Created by john on 2016/4/7.
 */
public class Sitefours {


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

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<SiteInfo> getList() {
        return list;
    }

    public void setList(List<SiteInfo> list) {
        this.list = list;
    }

    public Sitefours(int currentPage, int rows, int totalpage, List<SiteInfo> list) {
        this.currentPage = currentPage;
        this.rows = rows;
        this.totalpage = totalpage;
        this.list = list;
    }

    private int currentPage;
    private int rows;
    private int totalpage;
    private List<SiteInfo> list;
}
