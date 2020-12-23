package com.zaotao.daylucky.presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.zaotao.base.rx.RxBus;
import com.zaotao.base.rx.RxBusSubscriber;
import com.zaotao.base.rx.RxSchedulers;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.LuckDataManager;
import com.zaotao.daylucky.app.MD5Utils;
import com.zaotao.daylucky.base.BasePresenter;
import com.zaotao.daylucky.contract.DayLuckVipContract;
import com.zaotao.daylucky.module.api.ApiNetwork;
import com.zaotao.daylucky.module.api.ApiService;
import com.zaotao.daylucky.module.api.ApiSubscriber;
import com.zaotao.daylucky.module.api.BaseResult;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.module.event.SelectEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class DayLuckVipPresenter extends BasePresenter<DayLuckVipContract.View> implements DayLuckVipContract.Presenter {

    private ApiService apiService;

    @Override
    public void onPresenterCreated() {
        apiService = ApiNetwork.getNetService(ApiService.class);
    }

    @Override
    public void onPresenterDestroy() {
    }

    @Override
    public void registerSelectPosition() {
        RxBus.getDefault().toObservable(SelectEvent.class)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new RxBusSubscriber<SelectEvent>() {
                    @Override
                    public void onEvent(SelectEvent selectEvent) {
                        getView().onChangeConstellationIndex(selectEvent.getVar());
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }

    @Override
    public void initHomeLucky(int var, String mobile) {
        String key = "2ff63fe6443211ebb2cf00163e30eb65";
        String MD5code = mobile + var + key;
        String sign = MD5Utils.getMD5Code(MD5code);
        apiService.initVipLucky(var, mobile, sign)
                .map(new Function<BaseResult<LuckyVipEntity>, LuckyVipEntity>() {
                    @Override
                    public LuckyVipEntity apply(BaseResult<LuckyVipEntity> luckyVipResult) throws Exception {
                        return luckyVipResult.getData();
                    }
                })
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new ApiSubscriber<LuckyVipEntity>() {
                    @Override
                    public void onSuccess(LuckyVipEntity luckyVipEntity) {
                        getView().onSuccessLucky(luckyVipEntity);
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }

    @Override
    public List<FortuneContentEntity> initVipWeekFortuneList(LuckyVipEntity luckyVipEntity) {
        List<FortuneContentEntity> fortuneContentEntityList = new ArrayList<>();
        String cont1 = luckyVipEntity.getWeek().getCont1();
        String cont3 = luckyVipEntity.getWeek().getCont3();
        String cont5 = luckyVipEntity.getWeek().getCont5();
        String cont7 = luckyVipEntity.getWeek().getCont7();
        String cont9 = luckyVipEntity.getWeek().getCont9();
        if (TextUtils.isEmpty(cont1) || TextUtils.isEmpty(cont3) || TextUtils.isEmpty(cont5) || TextUtils.isEmpty(cont7) || TextUtils.isEmpty(cont9)) {
            return new ArrayList<>();
        } else {
            fortuneContentEntityList.add(new FortuneContentEntity(cont1));
            fortuneContentEntityList.add(new FortuneContentEntity(cont3));
            fortuneContentEntityList.add(new FortuneContentEntity(cont5));
            fortuneContentEntityList.add(new FortuneContentEntity(cont7));
            fortuneContentEntityList.add(new FortuneContentEntity(cont9));
        }
        return fortuneContentEntityList;
    }

    @Override
    public List<FortuneContentEntity> initMonthFortuneList(LuckyVipEntity luckyVipEntity) {
        List<FortuneContentEntity> fortuneContentEntityList = new ArrayList<>();
        String cont1 = luckyVipEntity.getYear().getCont1();
        String cont3 = luckyVipEntity.getYear().getCont3();
        String cont5 = luckyVipEntity.getYear().getCont5();
        String cont7 = luckyVipEntity.getYear().getCont7();
        String cont9 = luckyVipEntity.getYear().getCont9();
        if (luckyVipEntity.getYear().getLock() == 0) {
            return new ArrayList<>();
        } else {
            fortuneContentEntityList.add(new FortuneContentEntity(cont1));
            fortuneContentEntityList.add(new FortuneContentEntity(cont3));
            fortuneContentEntityList.add(new FortuneContentEntity(cont5));
            fortuneContentEntityList.add(new FortuneContentEntity(cont7));
            fortuneContentEntityList.add(new FortuneContentEntity(cont9));
        }
        return fortuneContentEntityList;
    }

    @Override
    public List<FortuneContentEntity> initVipYearFortuneList(LuckyVipEntity luckyVipEntity) {
        List<FortuneContentEntity> fortuneContentEntityList = new ArrayList<>();
        String cont1 = luckyVipEntity.getYear().getCont1();
        String cont3 = luckyVipEntity.getYear().getCont3();
        String cont5 = luckyVipEntity.getYear().getCont5();
        String cont7 = luckyVipEntity.getYear().getCont7();
        String cont9 = luckyVipEntity.getYear().getCont9();
        if (luckyVipEntity.getYear().getLock() == 0) {
            return new ArrayList<>();
        } else {
            fortuneContentEntityList.add(new FortuneContentEntity(cont1));
            fortuneContentEntityList.add(new FortuneContentEntity(cont3));
            fortuneContentEntityList.add(new FortuneContentEntity(cont5));
            fortuneContentEntityList.add(new FortuneContentEntity(cont7));
            fortuneContentEntityList.add(new FortuneContentEntity(cont9));
        }
        return fortuneContentEntityList;
    }

    @Override
    public void initVipWeekChartsList(Context context, LuckyVipEntity luckyVipEntity, LineChart luckyVipLineChart) {
        int todayPosition = 0;
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < luckyVipEntity.getWeek_charts().size(); i++) {
            LuckyTodayEntity luckyTodayEntity = luckyVipEntity.getWeek_charts().get(i);
            if (luckyTodayEntity.getX().equals("今天")) {
                todayPosition = i;
            }
            points.add(luckyTodayEntity.getY());
        }
        List<Entry> valuesCompPoint = new ArrayList<>();
        List<Entry> valuesCompNormal = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if (i == todayPosition) {
                valuesCompPoint.add(new Entry(i * 10, points.get(i)));
            }
            valuesCompNormal.add(new Entry(i * 10, points.get(i)));
        }
        Drawable lineDataSetDrawable = ContextCompat.getDrawable(context, R.drawable.ic_line_chart_fill_bg);
        LineDataSet lineDataSetPointStyle = new LineDataSet(valuesCompPoint, "");
        lineDataSetPointStyle.setColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetPointStyle.setDrawCircles(true);
        lineDataSetPointStyle.setCircleColor(Color.WHITE);
        lineDataSetPointStyle.setCircleRadius(5f);
        lineDataSetPointStyle.setCircleHoleRadius(4f);
        lineDataSetPointStyle.setCircleHoleColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetPointStyle.setDrawValues(true);
        lineDataSetPointStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetPointStyle.setLineWidth(3.5f);
        lineDataSetPointStyle.setDrawFilled(false);
        lineDataSetPointStyle.setFillDrawable(lineDataSetDrawable);
        lineDataSetPointStyle.setValueTextColor(Color.WHITE);

        LineDataSet lineDataSetNormalStyle = new LineDataSet(valuesCompNormal, "");
        lineDataSetNormalStyle.setColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetNormalStyle.setDrawCircles(false);
        lineDataSetNormalStyle.setDrawValues(false);
        lineDataSetNormalStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetNormalStyle.setLineWidth(3.5f);
        lineDataSetNormalStyle.setDrawFilled(false);
        lineDataSetNormalStyle.setFillDrawable(lineDataSetDrawable);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSetPointStyle);
        dataSets.add(lineDataSetNormalStyle);

        LineData lineData = new LineData(dataSets);

        luckyVipLineChart.setData(lineData);
        luckyVipLineChart.invalidate();
        luckyVipLineChart.setVisibility(View.VISIBLE);
    }

    @Override
    public void initVipYearChartsList(Context context, LuckyVipEntity luckyVipEntity, LineChart luckyVipLineChart) {
        int currentYear = 0;
        List<Integer> points = new ArrayList<>();
        for (int i = 0; i < luckyVipEntity.getYear_charts().size(); i++) {
            LuckyTodayEntity luckyTodayEntity = luckyVipEntity.getYear_charts().get(i);
            if (luckyTodayEntity.getX().equals(luckyVipEntity.getDate().getMonth())) {
                currentYear = i;
            }
            points.add(luckyTodayEntity.getY());
        }
        List<Entry> valuesCompPoint = new ArrayList<>();
        List<Entry> valuesCompNormal = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if (i == currentYear) {
                valuesCompPoint.add(new Entry(i * 10, points.get(i)));
            }
            valuesCompNormal.add(new Entry(i * 10, points.get(i)));
        }
        Drawable lineDataSetDrawable = ContextCompat.getDrawable(context, R.drawable.ic_line_chart_fill_bg);
        LineDataSet lineDataSetPointStyle = new LineDataSet(valuesCompPoint, "");
        lineDataSetPointStyle.setColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetPointStyle.setDrawCircles(true);
        lineDataSetPointStyle.setCircleColor(Color.WHITE);
        lineDataSetPointStyle.setCircleRadius(5f);
        lineDataSetPointStyle.setCircleHoleRadius(4f);
        lineDataSetPointStyle.setCircleHoleColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetPointStyle.setDrawValues(true);
        lineDataSetPointStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetPointStyle.setLineWidth(3.5f);
        lineDataSetPointStyle.setDrawFilled(false);
        lineDataSetPointStyle.setFillDrawable(lineDataSetDrawable);
        lineDataSetPointStyle.setValueTextColor(Color.WHITE);

        LineDataSet lineDataSetNormalStyle = new LineDataSet(valuesCompNormal, "");
        lineDataSetNormalStyle.setColor(ContextCompat.getColor(context, R.color.color6983FE));
        lineDataSetNormalStyle.setDrawCircles(false);
        lineDataSetNormalStyle.setDrawValues(false);
        lineDataSetNormalStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetNormalStyle.setLineWidth(3.5f);
        lineDataSetNormalStyle.setDrawFilled(false);
        lineDataSetNormalStyle.setFillDrawable(lineDataSetDrawable);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSetPointStyle);
        dataSets.add(lineDataSetNormalStyle);

        LineData lineData = new LineData(dataSets);

        luckyVipLineChart.setData(lineData);
        luckyVipLineChart.invalidate();
        luckyVipLineChart.setVisibility(View.VISIBLE);
    }


}
