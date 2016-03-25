package com.diandian.ycdyus.moneymanager.bean;

import java.io.Serializable;

/**
 * Created by cretin on 16/3/19.
 */
public class ManagerBean implements Serializable{
    public static final int CUSTOMER_IN = 1;
    public static final int CUSTOMER_OUT = 0;

    private String type;
    private double count;
    private int consumerType;
    private String instrument;
    private int resId;
    private int week;
    private String time;
    private int month;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(int consumerType) {
        this.consumerType = consumerType;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
