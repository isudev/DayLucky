package com.zaotao.daylucky.contract;

import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;

import java.util.List;

public interface DayLuckVipContract {

    interface View extends IView {

        void onSuccessLucky(LuckyVipEntity luckyVipEntity);

    }


    interface Presenter extends BaseSimplePresenter<View> {


        void initHomeLucky(int var,String mobile);

        List<FortuneContentEntity> initVipWeekFortuneList(LuckyVipEntity luckyVipEntity);

    }
}
