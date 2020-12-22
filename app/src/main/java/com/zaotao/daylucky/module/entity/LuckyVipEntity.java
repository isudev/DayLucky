package com.zaotao.daylucky.module.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description LuckyVipEntity
 * Created by wangisu@qq.com on 12/22/20.
 */
public class LuckyVipEntity implements Serializable {

    private LuckyContentEntity week;
    private LuckyContentEntity month;
    private LuckyContentEntity year;
    private List<LuckyTodayEntity> week_charts;
    private List<LuckyTodayEntity> month_charts;
    private List<LuckyTodayEntity> year_charts;
    private LuckyDateEntity date;


    public LuckyContentEntity getWeek() {
        return week;
    }

    public void setWeek(LuckyContentEntity week) {
        this.week = week;
    }

    public LuckyContentEntity getMonth() {
        return month;
    }

    public void setMonth(LuckyContentEntity month) {
        this.month = month;
    }

    public LuckyContentEntity getYear() {
        return year;
    }

    public void setYear(LuckyContentEntity year) {
        this.year = year;
    }

    public List<LuckyTodayEntity> getWeek_charts() {
        return week_charts;
    }

    public void setWeek_charts(List<LuckyTodayEntity> week_charts) {
        this.week_charts = week_charts;
    }

    public List<LuckyTodayEntity> getMonth_charts() {
        return month_charts;
    }

    public void setMonth_charts(List<LuckyTodayEntity> month_charts) {
        this.month_charts = month_charts;
    }

    public List<LuckyTodayEntity> getYear_charts() {
        return year_charts;
    }

    public void setYear_charts(List<LuckyTodayEntity> year_charts) {
        this.year_charts = year_charts;
    }

    public LuckyDateEntity getDate() {
        return date;
    }

    public void setDate(LuckyDateEntity date) {
        this.date = date;
    }
}
