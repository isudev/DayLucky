package com.zaotao.daylucky.presenter;

import android.widget.ImageView;

import com.google.gson.Gson;
import com.zaotao.base.rx.RxBus;
import com.zaotao.base.rx.RxBusSubscriber;
import com.zaotao.base.rx.RxSchedulers;
import com.zaotao.base.utils.NetworkUtils;
import com.zaotao.daylucky.App;
import com.zaotao.daylucky.app.ColorManager;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.app.LuckDataManager;
import com.zaotao.daylucky.base.BasePresenter;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.api.ApiNetwork;
import com.zaotao.daylucky.module.api.ApiService;
import com.zaotao.daylucky.module.api.ApiSubscriber;
import com.zaotao.daylucky.module.api.BaseResult;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyItemEntity;
import com.zaotao.daylucky.module.entity.SettingSelectEntity;
import com.zaotao.daylucky.module.entity.SettingStyleEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.event.SelectEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class DayLuckCorePresenter extends BasePresenter<DayLuckCoreContract.View> implements DayLuckCoreContract.Presenter {

    private static final String TAG = "MainHomePresenter";

    private ApiService apiService;

    @Override
    public void onPresenterCreated() {
        apiService = ApiNetwork.getNetService(ApiService.class);
    }

    @Override
    public void onPresenterDestroy() {
    }

    @Override
    public void registerSelectPosition(ImageView imageView) {
        RxBus.getDefault().toObservable(SelectEvent.class)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new RxBusSubscriber<SelectEvent>() {
                    @Override
                    public void onEvent(SelectEvent selectEvent) {
                        initHomeLucky(selectEvent.getVar());
                        imageView.setImageResource(LuckDataManager.getInstance().getImageRes());
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }

    @Override
    public void registerLuckyData() {
        RxBus.getDefault().toObservableSticky(LuckyEntity.class)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new RxBusSubscriber<LuckyEntity>() {
                    @Override
                    public void onEvent(LuckyEntity luckyEntity) {
                        getView().onSuccessLucky(luckyEntity);
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }

    @Override
    public void registerThemeInfo() {
        RxBus.getDefault().toObservable(ThemeEntity.class)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new RxBusSubscriber<ThemeEntity>() {
                    @Override
                    public void onEvent(ThemeEntity themeEntity) {
                        getView().onSuccessThemeInfo(themeEntity);
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }

    @Override
    public void initHomeLucky() {
        Observable.just("normal.json")
                .map(new Function<String, LuckyEntity>() {
                    @Override
                    public LuckyEntity apply(String fileName) throws Exception {
                        LuckyEntity luckyEntity = null;

                        try {
                            InputStream inputStream = App.getApplication().getApplicationContext().getAssets().open(fileName);

                            BufferedReader bufferedReader = new BufferedReader(
                                    new InputStreamReader(inputStream));
                            StringBuilder stringBuilder = new StringBuilder();
                            String line = null;

                            try {
                                while ((line = bufferedReader.readLine()) != null) {
                                    stringBuilder.append(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    inputStream.close();
                                    bufferedReader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }


                            String json = stringBuilder.toString();


                            Gson gson = new Gson();
                            luckyEntity = gson.fromJson(json, LuckyEntity.class);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return luckyEntity;
                    }
                })
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new ApiSubscriber<LuckyEntity>() {
                    @Override
                    public void onSuccess(LuckyEntity luckyEntity) {
                        ThemeEntity themeEntity = new ThemeEntity();
                        themeEntity.setBad(luckyEntity.getToday().getBad());
                        themeEntity.setLucky(luckyEntity.getToday().getLuck());
                        themeEntity.setText(luckyEntity.getToday().getCont());
                        themeEntity.setDay(DateUtils.formatDayText());
                        themeEntity.setMonth(DateUtils.formatMonthText());
                        themeEntity.setWeek(DateUtils.formatWeekText());
                        themeEntity.setLineColor(ColorManager.colorsLineBg[0][0]);
                        themeEntity.setBgColor(ColorManager.colorsLineBg[0][1]);
                        themeEntity.setDayColor(ColorManager.colorTextView);
                        themeEntity.setWeekColor(ColorManager.colorTextView);
                        themeEntity.setMonthColor(ColorManager.colorTextView);
                        themeEntity.setTextColor(ColorManager.colorTextContent);
                        themeEntity.setLuckyColor(ColorManager.normalLuckColor);
                        themeEntity.setBadColor(ColorManager.normalBadColor);

                        if (LuckDataManager.getInstance().isEmptyLocalTheme()) {
                            RxBus.getDefault().post(themeEntity);
                        } else {
                            themeEntity = LuckDataManager.getInstance().getThemeData();
                            themeEntity.setBad(luckyEntity.getToday().getBad());
                            themeEntity.setLucky(luckyEntity.getToday().getLuck());
                            themeEntity.setText(luckyEntity.getToday().getCont());
                            themeEntity.setDay(DateUtils.formatDayText());
                            themeEntity.setMonth(DateUtils.formatMonthText());
                            themeEntity.setWeek(DateUtils.formatWeekText());
                            LuckDataManager.getInstance().saveThemeData(themeEntity);
                            RxBus.getDefault().post(LuckDataManager.getInstance().getThemeData());
                        }
                        RxBus.getDefault().postSticky(luckyEntity);
                        getView().onSuccessLucky(luckyEntity);
                    }

                    @Override
                    public void onFailure(String errMsg) {

                    }
                });
    }


    @Override
    public void initHomeLucky(int position) {
        if (!NetworkUtils.isConnected()) {
            initHomeLucky();
        }
        apiService.initHomeLucky(position)
                .filter(new Predicate<BaseResult<LuckyEntity>>() {
                    @Override
                    public boolean test(BaseResult<LuckyEntity> luckyEntityResult) throws Exception {
                        return luckyEntityResult.success();
                    }
                })
                .map(new Function<BaseResult<LuckyEntity>, LuckyEntity>() {
                    @Override
                    public LuckyEntity apply(BaseResult<LuckyEntity> luckyEntityResult) throws Exception {
                        return luckyEntityResult.getData();
                    }
                })
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new ApiSubscriber<LuckyEntity>() {
                    @Override
                    public void onSuccess(LuckyEntity luckyEntity) {
                        ThemeEntity themeEntity = new ThemeEntity();
                        themeEntity.setBad(luckyEntity.getToday().getBad());
                        themeEntity.setLucky(luckyEntity.getToday().getLuck());
                        themeEntity.setText(luckyEntity.getToday().getCont());
                        themeEntity.setDay(DateUtils.formatDayText());
                        themeEntity.setMonth(DateUtils.formatMonthText());
                        themeEntity.setWeek(DateUtils.formatWeekText());
                        themeEntity.setLineColor(ColorManager.colorsLineBg[0][0]);
                        themeEntity.setBgColor(ColorManager.colorsLineBg[0][1]);
                        themeEntity.setDayColor(ColorManager.colorTextView);
                        themeEntity.setWeekColor(ColorManager.colorTextView);
                        themeEntity.setMonthColor(ColorManager.colorTextView);
                        themeEntity.setTextColor(ColorManager.colorTextContent);
                        themeEntity.setLuckyColor(ColorManager.normalLuckColor);
                        themeEntity.setBadColor(ColorManager.normalBadColor);

                        if (LuckDataManager.getInstance().isEmptyLocalTheme()) {
                            RxBus.getDefault().post(themeEntity);
                        } else {
                            themeEntity = LuckDataManager.getInstance().getThemeData();
                            themeEntity.setBad(luckyEntity.getToday().getBad());
                            themeEntity.setLucky(luckyEntity.getToday().getLuck());
                            themeEntity.setText(luckyEntity.getToday().getCont());
                            themeEntity.setDay(DateUtils.formatDayText());
                            themeEntity.setMonth(DateUtils.formatMonthText());
                            themeEntity.setWeek(DateUtils.formatWeekText());
                            LuckDataManager.getInstance().saveThemeData(themeEntity);
                            RxBus.getDefault().post(LuckDataManager.getInstance().getThemeData());
                        }
                        RxBus.getDefault().postSticky(luckyEntity);
                        getView().onSuccessLucky(luckyEntity);

                    }

                    @Override
                    public void onFailure(String errMsg) {
                        initHomeLucky();
                    }
                });
    }

    @Override
    public List<SettingSelectEntity> initSelectConstellationData() {
        List<SettingSelectEntity> settingSelectEntities = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            SettingSelectEntity settingSelectEntity = new SettingSelectEntity();
            settingSelectEntity.setName(Constants.CONSTELLATION_DESC[i]);
            settingSelectEntity.setImg(Constants.CONSTELLATION_IMG[i]);
            settingSelectEntities.add(settingSelectEntity);
        }
        return settingSelectEntities;
    }

    @Override
    public List<LuckyItemEntity> initFortuneLuckyData(List<String> contItems) {
        List<LuckyItemEntity> luckyItemEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LuckyItemEntity luckyItemEntity = new LuckyItemEntity();
            luckyItemEntity.setText(contItems.get(i));
            luckyItemEntity.setImg(Constants.FORTUNE_IMG[i]);
            luckyItemEntity.setLineImg(Constants.FORTUNE_LINE_IMG[i]);
            luckyItemEntity.setTitle(Constants.FORTUNE_DESC[i]);
            luckyItemEntityList.add(luckyItemEntity);
        }
        return luckyItemEntityList;
    }

    @Override
    public List<SettingStyleEntity> initSettingStyleData() {
        List<SettingStyleEntity> settingStyleEntityList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            settingStyleEntityList.add(new SettingStyleEntity(Constants.SETTING_STYLE[i]));
        }
        return settingStyleEntityList;
    }
}
