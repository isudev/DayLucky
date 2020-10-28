package com.zaotao.daylucky.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.MainHomeContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyItemEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.LuckyItemAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FortuneMonthFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {

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
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().registerLuckyData();

        homeDataBean = (LuckyEntity) getArguments().getSerializable("fragment_home_fortune_month");

        LuckyEntity.DateResultBean homeDataBeanMonth = homeDataBean.getMonth();
        fragmentHomeFortuneMonthDesc.setText(homeDataBean.getMonth().getCont());
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
        luckyItemEntityList.get(0).setText(homeDataBeanMonth.getCont1());

        luckyItemEntityList.get(1).setImg(R.drawable.ic_item_lucky_image1);
        luckyItemEntityList.get(1).setTitle("事业");
        luckyItemEntityList.get(1).setLineImg(R.drawable.ic_item_lucky_image1s);
        luckyItemEntityList.get(1).setText(homeDataBeanMonth.getCont3());


        luckyItemEntityList.get(2).setImg(R.drawable.ic_item_lucky_image2);
        luckyItemEntityList.get(2).setTitle("学业");
        luckyItemEntityList.get(2).setLineImg(R.drawable.ic_item_lucky_image2s);
        luckyItemEntityList.get(2).setText(homeDataBeanMonth.getCont5());


        luckyItemEntityList.get(3).setImg(R.drawable.ic_item_lucky_image3);
        luckyItemEntityList.get(3).setTitle("财运");
        luckyItemEntityList.get(3).setLineImg(R.drawable.ic_item_lucky_image3s);
        luckyItemEntityList.get(3).setText(homeDataBeanMonth.getCont7());



        luckyItemEntityList.get(4).setImg(R.drawable.ic_item_lucky_image4);
        luckyItemEntityList.get(4).setTitle("健康");
        luckyItemEntityList.get(4).setLineImg(R.drawable.ic_item_lucky_image4s);
        luckyItemEntityList.get(4).setText(homeDataBeanMonth.getCont9());



        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewFragmentHomeFortuneMonth.setLayoutManager(linearLayoutManager);
        luckyItemAdapter.notifyDataSetChanged(luckyItemEntityList);
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

        LuckyEntity.DateResultBean homeDataBeanMonth = homeDataBean.getMonth();
        fragmentHomeFortuneMonthDesc.setText(homeDataBean.getMonth().getCont());
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
        luckyItemEntityList.get(0).setText(homeDataBeanMonth.getCont1());

        luckyItemEntityList.get(1).setImg(R.drawable.ic_item_lucky_image1);
        luckyItemEntityList.get(1).setTitle("事业");
        luckyItemEntityList.get(1).setLineImg(R.drawable.ic_item_lucky_image1s);
        luckyItemEntityList.get(1).setText(homeDataBeanMonth.getCont3());


        luckyItemEntityList.get(2).setImg(R.drawable.ic_item_lucky_image2);
        luckyItemEntityList.get(2).setTitle("学业");
        luckyItemEntityList.get(2).setLineImg(R.drawable.ic_item_lucky_image2s);
        luckyItemEntityList.get(2).setText(homeDataBeanMonth.getCont5());


        luckyItemEntityList.get(3).setImg(R.drawable.ic_item_lucky_image3);
        luckyItemEntityList.get(3).setTitle("财运");
        luckyItemEntityList.get(3).setLineImg(R.drawable.ic_item_lucky_image3s);
        luckyItemEntityList.get(3).setText(homeDataBeanMonth.getCont7());



        luckyItemEntityList.get(4).setImg(R.drawable.ic_item_lucky_image4);
        luckyItemEntityList.get(4).setTitle("健康");
        luckyItemEntityList.get(4).setLineImg(R.drawable.ic_item_lucky_image4s);
        luckyItemEntityList.get(4).setText(homeDataBeanMonth.getCont9());



        LuckyItemAdapter luckyItemAdapter = new LuckyItemAdapter(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewFragmentHomeFortuneMonth.setLayoutManager(linearLayoutManager);
        luckyItemAdapter.notifyDataSetChanged(luckyItemEntityList);
        recyclerViewFragmentHomeFortuneMonth.setAdapter(luckyItemAdapter);
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }
}
