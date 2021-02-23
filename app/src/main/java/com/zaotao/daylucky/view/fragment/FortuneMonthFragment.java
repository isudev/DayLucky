package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.entity.LuckyContentEntity;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.LuckyContentAdapter;

import butterknife.BindView;

public class FortuneMonthFragment extends BaseFragment<DayLuckCorePresenter> implements DayLuckCoreContract.View {

    @BindView(R.id.fragment_home_fortune_month_text)
    TextView fragmentHomeFortuneMonthText;
    @BindView(R.id.recycler_view_fragment_home_fortune_month)
    RecyclerView recyclerViewFragmentHomeFortuneMonth;
    @BindView(R.id.fragment_home_fortune_month_desc)
    TextView fragmentHomeFortuneMonthDesc;

    private LuckyContentAdapter luckyContentAdapter;

    public static FortuneMonthFragment newInstance(LuckyEntity luckyEntity) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.LUCKY_PARAMS, luckyEntity);
        FortuneMonthFragment fragment = new FortuneMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_fortune_month;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().registerLuckyData();

        LuckyEntity luckyEntity = (LuckyEntity) getArguments().getSerializable(Constants.LUCKY_PARAMS);

        LuckyContentEntity luckyContentMonth = luckyEntity.getMonth();
        fragmentHomeFortuneMonthDesc.setText(luckyEntity.getMonth().getCont());
        /**
         * init list data
         */
        luckyContentAdapter = new LuckyContentAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewFragmentHomeFortuneMonth.setLayoutManager(linearLayoutManager);
        recyclerViewFragmentHomeFortuneMonth.setAdapter(luckyContentAdapter);
        luckyContentAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(luckyContentMonth));
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {
        LuckyContentEntity luckyContentMonth = luckyEntity.getMonth();
        fragmentHomeFortuneMonthDesc.setText(luckyEntity.getMonth().getCont());

        luckyContentAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(luckyContentMonth));
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }
}
