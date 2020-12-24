package com.zaotao.daylucky.app;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * Description StatisticsUtils
 * Created by wangisu@qq.com on 12/24/20.
 */
public class StatisticsUtils {

    public static void onClickWeekPay(Context mContext) {
        MobclickAgent.onEvent(mContext, "click_weekPay");
    }

    public static void onClickMonthPay(Context mContext) {
        MobclickAgent.onEvent(mContext, "click_monthPay");
    }

    public static void onClickYearPay(Context mContext) {
        MobclickAgent.onEvent(mContext, "click_yearPay");
    }

}
