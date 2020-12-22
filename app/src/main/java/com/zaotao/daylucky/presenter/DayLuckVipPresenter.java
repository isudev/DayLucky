package com.zaotao.daylucky.presenter;

import android.text.TextUtils;

import com.zaotao.base.rx.RxSchedulers;
import com.zaotao.daylucky.app.MD5Utils;
import com.zaotao.daylucky.base.BasePresenter;
import com.zaotao.daylucky.contract.DayLuckVipContract;
import com.zaotao.daylucky.module.api.ApiNetwork;
import com.zaotao.daylucky.module.api.ApiService;
import com.zaotao.daylucky.module.api.ApiSubscriber;
import com.zaotao.daylucky.module.api.BaseResult;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class DayLuckVipPresenter extends BasePresenter<DayLuckVipContract.View> implements DayLuckVipContract.Presenter {

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


}
