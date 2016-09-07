package com.example.john.ziteng.domain;

import java.util.List;

/**
 * 资讯新闻
 * Created by john on 2016/4/9.
 */
public class NewsTotal {
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

    public List<NewsInfo> getList() {
        return list;
    }

    public void setList(List<NewsInfo> list) {
        this.list = list;
    }

    public NewsTotal(int currentPage, int rows, int totalPage, List<NewsInfo> list) {
        this.currentPage = currentPage;
        this.rows = rows;
        this.totalPage = totalPage;
        this.list = list;
    }

    private int currentPage;
    private int rows;
    private int totalPage;
    private List<NewsInfo> list;
}
