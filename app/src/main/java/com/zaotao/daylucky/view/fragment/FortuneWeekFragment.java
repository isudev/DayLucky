package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.entity.LuckyContentEntity;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.LuckyContentAdapter;
import com.zaotao.daylucky.view.adapter.LuckyWeekLineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description HomeFortuneWeekFragment
 * Created by wangisu@qq.com on 2020-01-10
 */
public class FortuneWeekFragment extends BaseFragment<DayLuckCorePresenter> implements DayLuckCoreContract.View {

    @BindView(R.id.home_fortune_line_chart_text)
    TextView homeFortuneLineChartText;
    @BindView(R.id.home_fortune_line_chart_bottom_recycler_view)
    RecyclerView homeFortuneLineChartBottomRecyclerView;
    @BindView(R.id.recycler_view_fragment_home_fortune_week)
    RecyclerView recyclerViewFragmentHomeFortuneWeek;
    @BindView(R.id.fragment_home_fortune_week_desc)
    TextView fragmentHomeFortuneWeekDesc;
    @BindView(R.id.fragment_home_fortune_week_date)
    TextView fragmentHomeFortuneWeekDate;
    private LuckyWeekLineAdapter luckyWeekLineAdapter;

    private LuckyContentAdapter luckyContentAdapter;

    public static FortuneWeekFragment newInstance(LuckyEntity luckyEntity) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.LUCKY_PARAMS, luckyEntity);
        FortuneWeekFragment fragment = new FortuneWeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_fortune_week;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().registerLuckyData();

        LuckyEntity luckyEntity = (LuckyEntity) getArguments().getSerializable(Constants.LUCKY_PARAMS);
        fragmentHomeFortuneWeekDesc.setText(luckyEntity.getWeek().getCont());
        fragmentHomeFortuneWeekDate.setText(luckyEntity.getDate().getWeek());
        LuckyContentEntity luckyContentWeek = luckyEntity.getWeek();
        /**
         * init x data
         */
        List<LuckyTodayEntity> homeLuckyLineItemResults = new ArrayList<>();
        luckyWeekLineAdapter = new LuckyWeekLineAdapter(mContext);
        homeFortuneLineChartBottomRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
        homeFortuneLineChartBottomRecyclerView.setAdapter(luckyWeekLineAdapter);

        if (luckyEntity.getToday_affection() != null) {
            homeLuckyLineItemResults.addAll(luckyEntity.getToday_fortune());
        }
        luckyWeekLineAdapter.notifyDataSetChanged(homeLuckyLineItemResults);
        /**
         * init list data
         */
        luckyContentAdapter = new LuckyContentAdapter(mContext);
        recyclerViewFragmentHomeFortuneWeek.setLayoutManager(new LinearLayoutManager(mContext));
        luckyContentAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(luckyContentWeek));
        recyclerViewFragmentHomeFortuneWeek.setAdapter(luckyContentAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {
        fragmentHomeFortuneWeekDesc.setText(luckyEntity.getWeek().getCont());
        fragmentHomeFortuneWeekDate.setText(luckyEntity.getDate().getWeek());
        LuckyContentEntity luckyContentWeek = luckyEntity.getWeek();
        /**
         * init x data
         */
        List<LuckyTodayEntity> homeLuckyLineItemResults = new ArrayList<>();
        luckyWeekLineAdapter = new LuckyWeekLineAdapter(mContext);
        homeFortuneLineChartBottomRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
        homeFortuneLineChartBottomRecyclerView.setAdapter(luckyWeekLineAdapter);

        if (luckyEntity.getToday_affection() != null) {
            homeLuckyLineItemResults.addAll(luckyEntity.getToday_fortune());
        }
        luckyWeekLineAdapter.notifyDataSetChanged(homeLuckyLineItemResults);


        /**
         * init list data
         */
        luckyContentAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(luckyContentWeek));
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }
}
