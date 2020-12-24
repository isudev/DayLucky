package com.zaotao.daylucky.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.LuckDataManager;
import com.zaotao.daylucky.base.BaseActivity;
import com.zaotao.daylucky.module.event.SelectEvent;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.SettingSelectAdapter;

import butterknife.BindView;

public class SelectActivity extends BaseActivity<DayLuckCorePresenter> {

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
                sendEvent(new SelectEvent(position));
                LuckDataManager.getInstance().setSelectConstellationIndex(position);
                finish();
            }
        });
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

}