package com.zaotao.daylucky.view.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.rx.RxBus;
import com.zaotao.base.utils.ToastUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.ColorManager;
import com.zaotao.daylucky.app.LocalDataManager;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.MainHomeContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.ThemeStyleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ThemeFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {
    @BindView(R.id.recycler_view_themes)
    RecyclerView recyclerViewThemes;

    private List<ThemeEntity> themeEntityList = new ArrayList<>();

    private ThemeStyleAdapter themeStyleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().registerThemeInfo();

        themeStyleAdapter = new ThemeStyleAdapter(mContext);
        recyclerViewThemes.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewThemes.setAdapter(themeStyleAdapter);

    }

    @Override
    protected void initListener() {
        themeStyleAdapter.setOnItemPositionClickListener(new OnItemPositionClickListener() {
            @Override
            public void onClick(int position) {
                ThemeEntity themeEntity = themeEntityList.get(position);
                if (position > 0) {
                    themeEntity.setDayColor(ColorManager.normalWhiteColor);
                    themeEntity.setWeekColor(ColorManager.normalWhiteColor);
                    themeEntity.setMonthColor(ColorManager.normalWhiteColor);
                    themeEntity.setTextColor(ColorManager.normalWhiteColor);
                    themeEntity.setLuckyColor(ColorManager.normalWhiteColor);
                } else {
                    themeEntity.setLuckyColor(ColorManager.normalLuckColor);
                    themeEntity.setTextColor(ColorManager.colorTextContent);
                }

                LocalDataManager.getInstance().saveThemeData(themeEntity);
                RxBus.getDefault().post(themeEntity);
                showToast("change style success");
            }
        });
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {

    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity data) {
        themeEntityList.clear();
        LocalDataManager.getInstance().saveThemeData(data);

        for (int i = 0; i < 16; i++) {
            ThemeEntity themeEntity = new ThemeEntity();
            themeEntity.setDay(data.getDay());
            themeEntity.setWeek(data.getWeek());
            themeEntity.setMonth(data.getMonth());
            themeEntity.setText(data.getText());
            themeEntity.setLucky(data.getLucky());
            themeEntity.setBad(data.getBad());
            themeEntity.setLuckyColor(data.getLuckyColor());
            themeEntityList.add(themeEntity);
        }
        for (int i = 0; i < ColorManager.colorsLineBg.length; i++) {
            for (int j = 0; j < ColorManager.colorsLineBg[i].length; j++) {
                if (j == 0) {
                    themeEntityList.get(i).setLineColor(ColorManager.colorsLineBg[i][j]);
                    themeEntityList.get(i).setBadColor(ColorManager.colorsLineBg[i][j]);
                    if (i == 0) {
                        themeEntityList.get(i).setBadColor(ColorManager.normalBadColor);
                    }
                } else if (j == 1) {
                    themeEntityList.get(i).setBgColor(ColorManager.colorsLineBg[i][j]);
                }
            }
        }

        themeStyleAdapter.notifyDataSetChanged(themeEntityList);
    }
}
