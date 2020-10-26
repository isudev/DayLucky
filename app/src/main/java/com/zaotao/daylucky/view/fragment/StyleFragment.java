package com.zaotao.daylucky.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.view.RoundImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.LocalDataManager;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.MainHomeContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.SettingStyleEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.SettingStyleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.defaults.colorpicker.ColorPickerPopup;

public class StyleFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {
    @BindView(R.id.item_theme_style_day_text)
    TextView itemThemeStyleDayText;
    @BindView(R.id.item_theme_style_month_text)
    TextView itemThemeStyleMonthText;
    @BindView(R.id.item_theme_style_week_text)
    TextView itemThemeStyleWeekText;
    @BindView(R.id.item_theme_style_content_text)
    TextView itemThemeStyleContentText;
    @BindView(R.id.item_theme_style_day_lucky)
    TextView itemThemeStyleDayLucky;
    @BindView(R.id.item_theme_style_day_bad)
    TextView itemThemeStyleDayBad;
    @BindView(R.id.item_theme_style_day_lucky_text)
    TextView itemThemeStyleDayLuckyText;
    @BindView(R.id.item_theme_style_day_bad_text)
    TextView itemThemeStyleDayBadText;
    @BindView(R.id.item_theme_style_line)
    ImageView itemThemeStyleLine;
    @BindView(R.id.item_theme_style_bg)
    RoundImageView itemThemeStyleBg;
    @BindView(R.id.recycler_view_fragment_style)
    RecyclerView recyclerViewFragmentStyle;

    private int dayColor, weekColor, monthColor;
    private SettingStyleAdapter settingStyleAdapter;
    private List<SettingStyleEntity> settingStyleEntityList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style;
    }

    @Override
    protected MainHomePresenter initPresenter() {
        return new MainHomePresenter();
    }

    @Override
    protected void initViewData(View view) {


        getSupportPresenter().registerThemeInfo();


        settingStyleEntityList.add(new SettingStyleEntity("背景"));
        settingStyleEntityList.add(new SettingStyleEntity("日期"));
        settingStyleEntityList.add(new SettingStyleEntity("星期"));
        settingStyleEntityList.add(new SettingStyleEntity("月份"));

        settingStyleAdapter = new SettingStyleAdapter(mContext);
        settingStyleAdapter.notifyDataSetChanged(settingStyleEntityList);
        recyclerViewFragmentStyle.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerViewFragmentStyle.setAdapter(settingStyleAdapter);
    }

    @Override
    protected void initListener() {
        settingStyleAdapter.setOnItemPositionClickListener(new OnItemPositionClickListener() {
            @Override
            public void onClick(int position) {
                new ColorPickerPopup.Builder(mContext)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(false) // Enable brightness slider or not
                        .enableAlpha(false) // Enable alpha slider or not
                        .okTitle("确认")
                        .cancelTitle("")
                        .showIndicator(true)
                        .showValue(false)
                        .build()
                        .show(new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                settingStyleEntityList.get(position).setColor(color);
                                settingStyleAdapter.notifyDataSetChanged(settingStyleEntityList);
                                ThemeEntity themeEntity = LocalDataManager.getInstance().getThemeData();

                                switch (position) {
                                    case 0:

                                        themeEntity.setBgColor(color);

                                        break;
                                    case 1:
                                        dayColor = color;
                                        themeEntity.setDayColor(color);
                                        break;
                                    case 2:
                                        weekColor = color;
                                        themeEntity.setWeekColor(color);
                                        break;
                                    case 3:
                                        monthColor = color;
                                        themeEntity.setMonthColor(color);
                                        break;


                                }
                                LocalDataManager.getInstance().saveThemeData(themeEntity);
                                onSuccessThemeInfo(themeEntity);

                            }
                        });
            }
        });
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {

    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {
        itemThemeStyleDayText.setText(themeEntity.getDay());
        itemThemeStyleWeekText.setText(themeEntity.getWeek());
        itemThemeStyleMonthText.setText(themeEntity.getMonth());
        itemThemeStyleContentText.setText(themeEntity.getText());
        itemThemeStyleDayLuckyText.setText(themeEntity.getLucky());
        itemThemeStyleDayBadText.setText(themeEntity.getBad());
        itemThemeStyleDayBadText.setTextColor(themeEntity.getLineColor());
        itemThemeStyleLine.setBackgroundColor(themeEntity.getLineColor());
        itemThemeStyleBg.setImageDrawable(new ColorDrawable(themeEntity.getBgColor()));

        if (themeEntity.getBgColor() == -1) {
            itemThemeStyleDayText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleMonthText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleWeekText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleContentText.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
            itemThemeStyleDayLuckyText.setTextColor(ContextCompat.getColor(mContext, R.color.color85E9E6));
        } else {
            itemThemeStyleDayText.setTextColor(Color.WHITE);
            itemThemeStyleMonthText.setTextColor(Color.WHITE);
            itemThemeStyleWeekText.setTextColor(Color.WHITE);
            itemThemeStyleContentText.setTextColor(Color.WHITE);
            itemThemeStyleDayLuckyText.setTextColor(Color.WHITE);
            itemThemeStyleDayLucky.setTextColor(Color.WHITE);
            itemThemeStyleDayBad.setTextColor(Color.WHITE);
        }

        if (dayColor != 0) {
            itemThemeStyleDayText.setTextColor(dayColor);
        }
        if (weekColor != 0) {
            itemThemeStyleWeekText.setTextColor(weekColor);
        }
        if (monthColor != 0) {
            itemThemeStyleMonthText.setTextColor(monthColor);
        }
    }
}
