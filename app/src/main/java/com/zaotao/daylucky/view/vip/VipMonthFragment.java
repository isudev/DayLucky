package com.zaotao.daylucky.view.vip;

import android.os.Bundle;
import android.view.View;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;

/**
 * Description VipMonthFragment
 * Created by wangisu@qq.com on 12/22/20.
 */
public class VipMonthFragment extends BaseFragment<DayLuckCorePresenter> {

    public static VipMonthFragment newInstance(LuckyVipEntity luckyVipEntity) {
        Bundle args = new Bundle();
        args.putSerializable("fragment_lucky_vip_month", luckyVipEntity);
        VipMonthFragment fragment = new VipMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lucky_vip_month;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {

    }

    @Override
    protected void initListener() {

    }
}
