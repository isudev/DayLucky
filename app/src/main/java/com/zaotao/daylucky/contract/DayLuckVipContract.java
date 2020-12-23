package com.zaotao.daylucky.contract;

import android.content.Context;

import com.github.mikephil.charting.charts.LineChart;
import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;

import java.util.List;

public interface DayLuckVipContract {

    interface View extends IView {

        void onSuccessLucky(LuckyVipEntity luckyVipEntity);

    }


    interface Presenter extends BaseSimplePresenter<View> {


        void initHomeLucky(int var,String mobile);

        List<FortuneContentEntity> initVipWeekFortuneList(LuckyVipEntity luckyVipEntity);

        List<FortuneContentEntity> initMonthFortuneList(LuckyVipEntity luckyVipEntity);

        List<FortuneContentEntity> initVipYearFortuneList(LuckyVipEntity luckyVipEntity);

        void initVipWeekChartsList(Context context, LuckyVipEntity luckyVipEntity, LineChart luckyVipLineChart);

        void initVipYearChartsList(Context context, LuckyVipEntity luckyVipEntity, LineChart luckyVipLineChart);

    }
}
