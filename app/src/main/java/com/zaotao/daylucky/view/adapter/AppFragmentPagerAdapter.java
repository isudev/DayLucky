package com.zaotao.daylucky.view.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description AppFragmentPagerAdapter
 * Created by wangisu@qq.com on 2019-12-27
 */
public class AppFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public AppFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
