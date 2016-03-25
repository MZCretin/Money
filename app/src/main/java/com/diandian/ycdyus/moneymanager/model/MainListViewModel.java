package com.diandian.ycdyus.moneymanager.model;

import com.diandian.ycdyus.moneymanager.bean.ManagerBean;

import java.util.List;

/**
 * Created by ycdyus on 2016/2/29.
 */
public class MainListViewModel {
    private double shouru;
    private double zhichu;
    private List<ManagerBean> list;
    private int week;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<ManagerBean> getList() {
        return list;
    }

    public void setList(List<ManagerBean> list) {
        this.list = list;
    }

    public double getShouru() {
        return shouru;
    }

    public void setShouru(double shouru) {
        this.shouru = shouru;
    }

    public double getZhichu() {
        return zhichu;
    }

    public void setZhichu(double zhichu) {
        this.zhichu = zhichu;
    }
}
