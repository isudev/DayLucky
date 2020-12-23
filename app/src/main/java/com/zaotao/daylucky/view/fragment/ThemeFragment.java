package com.zaotao.daylucky.view.fragment;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.zaotao.base.utils.ToastUtils;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.adapter.ThemeStyleAdapter;

import java.util.List;

import butterknife.BindView;

public class ThemeFragment extends BaseFragment<DayLuckCorePresenter> implements DayLuckCoreContract.View {
    @BindView(R.id.recycler_view_themes)
    RecyclerView recyclerViewThemes;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    private List<ThemeEntity> themeEntityList;

    private ThemeStyleAdapter themeStyleAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {
        toolbarLayout.setTitle(getString(R.string.main_bottom_bar_text_theme));
        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(mContext, R.color.color333333));
        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(mContext, R.color.color333333));
        getSupportPresenter().registerThemeInfo();

        themeStyleAdapter = new ThemeStyleAdapter(mContext);
        recyclerViewThemes.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewThemes.setAdapter(themeStyleAdapter);

    }

    @Override
    protected void initListener() {
        themeStyleAdapter.setOnItemPositionClickListener(position -> {
            ThemeEntity themeEntity = themeEntityList.get(position);
            getSupportPresenter().selectChangeTheme(position, themeEntity);
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

        themeEntityList = getSupportPresenter().initThemeList(data);

        themeStyleAdapter.notifyDataSetChanged(this.themeEntityList);
    }
}
