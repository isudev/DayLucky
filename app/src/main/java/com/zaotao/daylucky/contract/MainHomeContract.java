package com.zaotao.daylucky.contract;

import android.widget.ImageView;

import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;

public interface MainHomeContract {

    interface View extends IView {

        void onSuccessLucky(LuckyEntity luckyEntity);

        void onSuccessThemeInfo(ThemeEntity themeEntity);
    }


    interface Presenter extends BaseSimplePresenter<View> {

        void registerSelectPosition(ImageView imageView);

        void registerLuckyData();

        void registerThemeInfo();

        void initHomeLucky(int var);

    }
}
