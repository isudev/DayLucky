package com.zaotao.daylucky.contract;

import android.widget.ImageView;

import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyContentEntity;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.SettingSelectEntity;
import com.zaotao.daylucky.module.entity.SettingStyleEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;

import java.util.List;

public interface DayLuckCoreContract {

    interface View extends IView {

        void onSuccessLucky(LuckyEntity luckyEntity);

        void onSuccessThemeInfo(ThemeEntity themeEntity);
    }


    interface Presenter extends BaseSimplePresenter<View> {

        void registerSelectPosition(ImageView imageView);

        void registerLuckyData();

        void registerThemeInfo();

        void initHomeLucky();

        void initHomeLucky(int var);

        void selectChangeTheme(int position, ThemeEntity themeEntity);

        void settingThemeStyle(int position,int color);

        List<ThemeEntity> initThemeList(ThemeEntity themeEntity);

        List<SettingSelectEntity> initSelectConstellationData();

        List<FortuneContentEntity> initFortuneLuckyData(LuckyContentEntity luckyContentEntity);

        List<SettingStyleEntity> initSettingStyleData();

    }
}
