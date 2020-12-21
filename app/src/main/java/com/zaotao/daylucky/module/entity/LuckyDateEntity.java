package com.zaotao.daylucky.module.entity;

import java.io.Serializable;

/**
 * Description DataEntity
 * Created by wangisu@qq.com on 12/21/20.
 */
public class LuckyDateEntity implements Serializable {
    /**
     * time : 1585733766
     * year : 2020
     * month : 04
     * day : 01
     * weekday : 4
     * month_abbr : Apr.
     * week : 03.30-04.05/2020
     */

    private int time;
    private String year;
    private String month;
    private String day;
    private int weekday;
    private String month_abbr;
    private String week;


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getMonth_abbr() {
        return month_abbr;
    }

    public void setMonth_abbr(String month_abbr) {
        this.month_abbr = month_abbr;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
