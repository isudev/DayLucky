package com.zaotao.daylucky.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.rx.RxBus;
import com.zaotao.daylucky.R;
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
        List<SettingSelectEntity> settingSelectEntities = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            settingSelectEntities.add(new SettingSelectEntity());
        }

        settingSelectEntities.get(0).setImg(R.drawable.ic_item_setting_select_view0);
        settingSelectEntities.get(0).setName("白羊座");

        settingSelectEntities.get(1).setImg(R.drawable.ic_item_setting_select_view1);
        settingSelectEntities.get(1).setName("金牛座");

        settingSelectEntities.get(2).setImg(R.drawable.ic_item_setting_select_view2);
        settingSelectEntities.get(2).setName("双子座");

        settingSelectEntities.get(3).setImg(R.drawable.ic_item_setting_select_view3);
        settingSelectEntities.get(3).setName("巨蟹座");

        settingSelectEntities.get(4).setImg(R.drawable.ic_item_setting_select_view4);
        settingSelectEntities.get(4).setName("狮子座");

        settingSelectEntities.get(5).setImg(R.drawable.ic_item_setting_select_view5);
        settingSelectEntities.get(5).setName("处女座");

        settingSelectEntities.get(6).setImg(R.drawable.ic_item_setting_select_view6);
        settingSelectEntities.get(6).setName("天秤座");

        settingSelectEntities.get(7).setImg(R.drawable.ic_item_setting_select_view7);
        settingSelectEntities.get(7).setName("天蝎座");

        settingSelectEntities.get(8).setImg(R.drawable.ic_item_setting_select_view8);
        settingSelectEntities.get(8).setName("射手座");

        settingSelectEntities.get(9).setImg(R.drawable.ic_item_setting_select_view9);
        settingSelectEntities.get(9).setName("摩羯座");

        settingSelectEntities.get(10).setImg(R.drawable.ic_item_setting_select_view10);
        settingSelectEntities.get(10).setName("水瓶座");

        settingSelectEntities.get(11).setImg(R.drawable.ic_item_setting_select_view11);
        settingSelectEntities.get(11).setName("双鱼座");

         settingSelectAdapter = new SettingSelectAdapter(mContext);
        settingSelectAdapter.notifyDataSetChanged(settingSelectEntities);
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