package com.zaotao.daylucky.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseActivity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.AppFragmentPagerAdapter;
import com.zaotao.daylucky.widget.navigation.BottomNavigationView;
import com.zaotao.daylucky.widget.viewpager.NoScrollViewPager;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity<DayLuckCorePresenter>  {

    @BindView(R.id.view_pager_main)
    NoScrollViewPager viewPagerMain;
    @BindView(R.id.main_bottom_view)
    BottomNavigationView mainBottomView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {

        List<Fragment> fragments = getSupportPresenter().initMainFragments();
        viewPagerMain.setAdapter(new AppFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        viewPagerMain.setOffscreenPageLimit(fragments.size());

        showFragment(mainBottomView.getTabLuckyIndex());
    }

    private void showFragment(int position) {
        viewPagerMain.setCurrentItem(position, false);
    }
    @Override
    protected void initListener() {
        mainBottomView.setOnItemPositionClickListener(this::showFragment);
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}