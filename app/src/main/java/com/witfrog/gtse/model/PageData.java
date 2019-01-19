package com.witfrog.gtse.model;

import java.util.List;

public class PageData<T> {

    private int     morePage;
    private int     page;
    private List<T> list;

    public int getMorePage() {
        return morePage;
    }

    public void setMorePage(int morePage) {
        this.morePage = morePage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
