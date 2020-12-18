package com.zaotao.daylucky.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.rx.RxBus;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.LocalDataManager;
import com.zaotao.daylucky.base.BaseActivity;
import com.zaotao.daylucky.module.entity.SettingSelectEntity;
import com.zaotao.daylucky.module.event.SelectEvent;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.SettingSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectActivity extends BaseActivity<MainHomePresenter> {

    @BindView(R.id.recycler_view_select)
    RecyclerView recyclerViewSelect;
    @BindView(R.id.select_back)
    ImageView selectBack;

    private SettingSelectAdapter settingSelectAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        settingSelectAdapter = new SettingSelectAdapter(mContext);
        settingSelectAdapter.notifyDataSetChanged(getSupportPresenter().initSelectConstellationData());
        recyclerViewSelect.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerViewSelect.setAdapter(settingSelectAdapter);
    }

    @Override
    protected void initListener() {
        selectBack.setOnClickListener(v -> finish());

        settingSelectAdapter.setOnItemPositionClickListener(new OnItemPositionClickListener() {
            @Override
            public void onClick(int position) {
                RxBus.getDefault().post(new SelectEvent(position));
                LocalDataManager.getInstance().setTipsFromHomeGuidePage(position);
                finish();
            }
        });
    }

    @Override
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}