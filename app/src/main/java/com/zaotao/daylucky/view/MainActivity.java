package com.zaotao.daylucky.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseActivity;
import com.zaotao.daylucky.view.fragment.LuckyFragment;
import com.zaotao.daylucky.view.fragment.StyleFragment;
import com.zaotao.daylucky.view.fragment.ThemeFragment;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.AppFragmentPagerAdapter;
import com.zaotao.daylucky.widget.viewpager.NoScrollViewPager;
import com.zaotao.daylucky.widget.navigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.zaotao.daylucky.widget.navigation.BottomNavigationView.TAB_LUCKY;

public class MainActivity extends BaseActivity<MainHomePresenter>  {

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
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(new LuckyFragment());
        mFragments.add(new ThemeFragment());
        mFragments.add(new StyleFragment());

        viewPagerMain.setAdapter(new AppFragmentPagerAdapter(getSupportFragmentManager(), mFragments));

        viewPagerMain.setOffscreenPageLimit(3);

        showFragment(TAB_LUCKY);
    }

    private void showFragment(int position) {
        viewPagerMain.setCurrentItem(position, false);
    }
    @Override
    protected void initListener() {
        mainBottomView.setOnItemPositionClickListener(this::showFragment);
    }

    @Override
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}