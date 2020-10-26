package com.zaotao.daylucky.base;

import android.view.View;

public abstract class BaseSimpleFragment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected abstract int getLayoutId();

    @Override
    protected abstract void initViewData(View view);
}
