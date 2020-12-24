package com.zaotao.daylucky.module.entity;

import com.zaotao.daylucky.app.ColorsManager;

public class ThemeEntity {
    private String day;
    private String week;
    private String month;
    private int lineColor;
    private int bgColor;
    private int dayColor = ColorsManager.colorTextView;
    private int weekColor = ColorsManager.colorTextView;
    private int monthColor = ColorsManager.colorTextView;
    private String lucky;
    private String bad;
    private String text;
    private int luckyColor;
    private int badColor;
    private int textColor;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getDayColor() {
        return dayColor;
    }

    public void setDayColor(int dayColor) {
        this.dayColor = dayColor;
    }

    public int getWeekColor() {
        return weekColor;
    }

    public void setWeekColor(int weekColor) {
        this.weekColor = weekColor;
    }

    public int getMonthColor() {
        return monthColor;
    }

    public void setMonthColor(int monthColor) {
        this.monthColor = monthColor;
    }

    public String getLucky() {
        return lucky;
    }

    public void setLucky(String lucky) {
        this.lucky = lucky;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLuckyColor() {
        return luckyColor;
    }

    public void setLuckyColor(int luckyColor) {
        this.luckyColor = luckyColor;
    }

    public int getBadColor() {
        return badColor;
    }

    public void setBadColor(int badColor) {
        this.badColor = badColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
