package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.entity.LuckyContentEntity;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.LuckyItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FortuneMonthFragment extends BaseFragment<DayLuckCorePresenter> implements DayLuckCoreContract.View {

    @BindView(R.id.fragment_home_fortune_month_text)
    TextView fragmentHomeFortuneMonthText;
    @BindView(R.id.recycler_view_fragment_home_fortune_month)
    RecyclerView recyclerViewFragmentHomeFortuneMonth;
    @BindView(R.id.fragment_home_fortune_month_desc)
    TextView fragmentHomeFortuneMonthDesc;
    private LuckyEntity homeDataBean;

    public static FortuneMonthFragment newInstance(LuckyEntity luckyEntity) {
        Bundle args = new Bundle();
        args.putSerializable("fragment_home_fortune_month", luckyEntity);
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

        homeDataBean = (LuckyEntity) getArguments().getSerializable("fragment_home_fortune_month");

        LuckyContentEntity luckyContentMonth = homeDataBean.getMonth();
        fragmentHomeFortuneMonthDesc.setText(homeDataBean.getMonth().getCont());
        /**
         * init list data
         */
        List<String> contItems = new ArrayList<>();
        contItems.add(luckyContentMonth.getCont1());
        contItems.add(luckyContentMonth.getCont3());
        contItems.add(luckyContentMonth.getCont5());
        contItems.add(luckyContentMonth.getCont7());
        contItems.add(luckyContentMonth.getCont9());
        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewFragmentHomeFortuneMonth.setLayoutManager(linearLayoutManager);
        luckyItemAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(contItems));
        recyclerViewFragmentHomeFortuneMonth.setAdapter(luckyItemAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {

        homeDataBean = luckyEntity;

        LuckyContentEntity luckyContentMonth = homeDataBean.getMonth();
        fragmentHomeFortuneMonthDesc.setText(homeDataBean.getMonth().getCont());
        /**
         * init list data
         */
        List<String> contItems = new ArrayList<>();
        contItems.add(luckyContentMonth.getCont1());
        contItems.add(luckyContentMonth.getCont3());
        contItems.add(luckyContentMonth.getCont5());
        contItems.add(luckyContentMonth.getCont7());
        contItems.add(luckyContentMonth.getCont9());
        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewFragmentHomeFortuneMonth.setLayoutManager(linearLayoutManager);
        luckyItemAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(contItems));
        recyclerViewFragmentHomeFortuneMonth.setAdapter(luckyItemAdapter);
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }
}
