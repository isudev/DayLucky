package com.zaotao.daylucky.app;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.text.format.DateUtils.formatElapsedTime;

/**
 * Description DatabaseUtils
 * Created by wangisu@qq.com on 2019-08-02
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {
    public static String formatDayText() {
        return new SimpleDateFormat("dd").format(System.currentTimeMillis());

    }
    public static String formatWeekText() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String weekString = new SimpleDateFormat(week[calendar.get(Calendar.DAY_OF_WEEK) - 1]).format(System.currentTimeMillis());
        if (weekString.equals("星期日")) {
            weekString = "Sunday";
        } else if (weekString.equals("星期一")) {
            weekString = "Monday";
        } else if (weekString.equals("星期二")) {
            weekString = "Tuesday";
        } else if (weekString.equals("星期三")) {
            weekString = "Wednesday";
        } else if (weekString.equals("星期四")) {
            weekString = "Thursday";
        } else if (weekString.equals("星期五")) {
            weekString = "Friday";
        } else if (weekString.equals("星期六")) {
            weekString = "Saturday";
        }
        return weekString;
    }

    public static String formatMonthText() {
        String monthString = new SimpleDateFormat("MM").format(System.currentTimeMillis());
        if (monthString.equals("1")) {
            monthString = "January";
        } else if (monthString.equals("2")) {
            monthString = "February";
        } else if (monthString.equals("3")) {
            monthString = "March";
        } else if (monthString.equals("4")) {
            monthString = "April";
        } else if (monthString.equals("5")) {
            monthString = "May";
        } else if (monthString.equals("6")) {
            monthString = "June";
        } else if (monthString.equals("7")) {
            monthString = "July";
        } else if (monthString.equals("8")) {
            monthString = "August";
        } else if (monthString.equals("9")) {
            monthString = "September";
        } else if (monthString.equals("10")) {
            monthString = "October";
        } else if (monthString.equals("11")) {
            monthString = "November";
        } else if (monthString.equals("12")) {
            monthString = "December";
        }
        return monthString;
    }
}
