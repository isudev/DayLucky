package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.utils.ToastUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.MainHomeContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyWeekEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.HomeFortuneWeekLineChartXAdapter;
import com.zaotao.daylucky.view.adapter.LuckyItemAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description HomeFortuneWeekFragment
 * Created by wangisu@qq.com on 2020-01-10
 */
public class FortuneWeekFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {

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
    private LuckyEntity homeDataBean;
    private HomeFortuneWeekLineChartXAdapter homeFortuneLineChartXAdapter;

    public static FortuneWeekFragment newInstance(LuckyEntity luckyEntity) {
        Bundle args = new Bundle();
        args.putSerializable("fragment_home_fortune_week", luckyEntity);
        FortuneWeekFragment fragment = new FortuneWeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_fortune_week;
    }

    @Override
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().registerLuckyData();

        homeDataBean = (LuckyEntity) getArguments().getSerializable("fragment_home_fortune_week");
        fragmentHomeFortuneWeekDesc.setText(homeDataBean.getWeek().getCont());
        fragmentHomeFortuneWeekDate.setText(homeDataBean.getDate().getWeek());
        LuckyEntity.WeekBean homeDataBeanWeek = homeDataBean.getWeek();
        /**
         * init x data
         */
        List<LuckyWeekEntity> homeLuckyLineItemResults = new ArrayList<>();
        homeFortuneLineChartXAdapter = new HomeFortuneWeekLineChartXAdapter(mContext);
        homeFortuneLineChartBottomRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
        homeFortuneLineChartBottomRecyclerView.setAdapter(homeFortuneLineChartXAdapter);

        if (homeDataBean != null && homeDataBean.getToday_affection() != null) {
            for (LuckyEntity.FortuneXYBean xyBean :
                    homeDataBean.getToday_affection()) {
                homeLuckyLineItemResults.add(new LuckyWeekEntity(xyBean.getX(), xyBean.getY()));
            }
        }
        homeFortuneLineChartXAdapter.notifyDataSetChanged(homeLuckyLineItemResults, DateUtils.WEEK_TIMES);


        /**
         * init list data
         */
        List<String> contItems = new ArrayList<>();
        contItems.add(homeDataBeanWeek.getCont1());
        contItems.add(homeDataBeanWeek.getCont3());
        contItems.add(homeDataBeanWeek.getCont5());
        contItems.add(homeDataBeanWeek.getCont7());
        contItems.add(homeDataBeanWeek.getCont9());
        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        recyclerViewFragmentHomeFortuneWeek.setLayoutManager(new LinearLayoutManager(mContext));
        luckyItemAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(contItems));
        recyclerViewFragmentHomeFortuneWeek.setAdapter(luckyItemAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {


        homeDataBean = luckyEntity;
        fragmentHomeFortuneWeekDesc.setText(homeDataBean.getWeek().getCont());
        fragmentHomeFortuneWeekDate.setText(homeDataBean.getDate().getWeek());
        LuckyEntity.WeekBean homeDataBeanWeek = homeDataBean.getWeek();
        /**
         * init x data
         */
        List<LuckyWeekEntity> homeLuckyLineItemResults = new ArrayList<>();
        homeFortuneLineChartXAdapter = new HomeFortuneWeekLineChartXAdapter(mContext);
        homeFortuneLineChartBottomRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 7));
        homeFortuneLineChartBottomRecyclerView.setAdapter(homeFortuneLineChartXAdapter);

        if (homeDataBean != null && homeDataBean.getToday_affection() != null) {
            for (LuckyEntity.FortuneXYBean xyBean :
                    homeDataBean.getToday_affection()) {
                homeLuckyLineItemResults.add(new LuckyWeekEntity(xyBean.getX(), xyBean.getY()));
            }
        }
        homeFortuneLineChartXAdapter.notifyDataSetChanged(homeLuckyLineItemResults, DateUtils.WEEK_TIMES);


        /**
         * init list data
         */
        List<String> contItems = new ArrayList<>();
        contItems.add(homeDataBeanWeek.getCont1());
        contItems.add(homeDataBeanWeek.getCont3());
        contItems.add(homeDataBeanWeek.getCont5());
        contItems.add(homeDataBeanWeek.getCont7());
        contItems.add(homeDataBeanWeek.getCont9());
        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        recyclerViewFragmentHomeFortuneWeek.setLayoutManager(new LinearLayoutManager(mContext));
        luckyItemAdapter.notifyDataSetChanged(getSupportPresenter().initFortuneLuckyData(contItems));
        recyclerViewFragmentHomeFortuneWeek.setAdapter(luckyItemAdapter);
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }
}
