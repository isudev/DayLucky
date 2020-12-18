package com.zaotao.daylucky.contract;

import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyItemEntity;
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

        void initHomeLucky(int var);

        List<Fragment> initMainFragments();

        List<SettingSelectEntity> initSelectConstellationData();

        List<LuckyItemEntity> initFortuneLuckyData(List<String> contItems);

        List<SettingStyleEntity> initSettingStyleData();

    }
}
