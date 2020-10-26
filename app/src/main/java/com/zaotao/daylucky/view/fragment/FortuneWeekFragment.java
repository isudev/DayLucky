package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.utils.ToastUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyItemEntity;
import com.zaotao.daylucky.module.entity.LuckyWeekEntity;
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
public class FortuneWeekFragment extends BaseFragment<MainHomePresenter> {

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
    private String[] weekTimes = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

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
        homeFortuneLineChartXAdapter.notifyDataSetChanged(homeLuckyLineItemResults, weekTimes);


        /**
         * init list data
         */
        List<LuckyItemEntity> luckyItemEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LuckyItemEntity luckyItemEntity = new LuckyItemEntity();
            luckyItemEntityList.add(luckyItemEntity);
        }
        luckyItemEntityList.get(0).setImg(R.drawable.ic_item_lucky_image0);
        luckyItemEntityList.get(0).setTitle("感情");
        luckyItemEntityList.get(0).setLineImg(R.drawable.ic_item_lucky_image0s);
        luckyItemEntityList.get(0).setText(homeDataBeanWeek.getCont1());

        luckyItemEntityList.get(1).setImg(R.drawable.ic_item_lucky_image1);
        luckyItemEntityList.get(1).setTitle("事业");
        luckyItemEntityList.get(1).setLineImg(R.drawable.ic_item_lucky_image1s);
        luckyItemEntityList.get(1).setText(homeDataBeanWeek.getCont3());


        luckyItemEntityList.get(2).setImg(R.drawable.ic_item_lucky_image2);
        luckyItemEntityList.get(2).setTitle("学业");
        luckyItemEntityList.get(2).setLineImg(R.drawable.ic_item_lucky_image2s);
        luckyItemEntityList.get(2).setText(homeDataBeanWeek.getCont5());

        luckyItemEntityList.get(3).setImg(R.drawable.ic_item_lucky_image3);
        luckyItemEntityList.get(3).setTitle("财运");
        luckyItemEntityList.get(3).setLineImg(R.drawable.ic_item_lucky_image3s);
        luckyItemEntityList.get(3).setText(homeDataBeanWeek.getCont7());


        luckyItemEntityList.get(4).setImg(R.drawable.ic_item_lucky_image4);
        luckyItemEntityList.get(4).setTitle("健康");
        luckyItemEntityList.get(4).setLineImg(R.drawable.ic_item_lucky_image4s);
        luckyItemEntityList.get(4).setText(homeDataBeanWeek.getCont9());

        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        recyclerViewFragmentHomeFortuneWeek.setLayoutManager(new LinearLayoutManager(mContext));
        luckyItemAdapter.notifyDataSetChanged(luckyItemEntityList);
        recyclerViewFragmentHomeFortuneWeek.setAdapter(luckyItemAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }
}
