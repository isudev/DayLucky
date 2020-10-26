package com.zaotao.daylucky.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;

public abstract class BaseSimpleActivity extends BaseActivity {

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected abstract @LayoutRes
    int getLayoutId();

    @Override
    protected abstract void initCreate(Bundle savedInstanceState);

    @Override
    protected abstract void initListener();
}
