package com.zaotao.daylucky.view.fragment;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.utils.ScreenUtils;
import com.zaotao.base.view.RoundImageView;
import com.zaotao.daylucky.App;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.ColorManager;
import com.zaotao.daylucky.app.LocalDataManager;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.MainHomeContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.SettingStyleEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.presenter.MainHomePresenter;
import com.zaotao.daylucky.view.adapter.SettingStyleAdapter;
import com.zaotao.daylucky.widget.core.DayLuckyAppWidget;
import com.zaotao.daylucky.widget.core.UpdateAppWidget;

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
        settingStyleEntityList.add(new SettingStyleEntity("运势"));
        settingStyleEntityList.add(new SettingStyleEntity("宜"));
        settingStyleEntityList.add(new SettingStyleEntity("忌"));

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
                                        themeEntity.setDayColor(color);
                                        break;
                                    case 2:
                                        themeEntity.setWeekColor(color);
                                        break;
                                    case 3:
                                        themeEntity.setMonthColor(color);
                                        break;
                                    case 4:
                                        themeEntity.setTextColor(color);
                                        break;
                                    case 5:
                                        themeEntity.setLuckyColor(color);
                                        break;
                                    case 6:
                                        themeEntity.setBadColor(color);
                                        break;
                                }
                                LocalDataManager.getInstance().saveThemeData(themeEntity);
                                onSuccessThemeInfo(themeEntity);

                            }
                        });
                UpdateAppWidget.getInstance().updateAppWidget();
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
        itemThemeStyleDayBadText.setTextColor(themeEntity.getBadColor());
        itemThemeStyleLine.setBackgroundColor(themeEntity.getLineColor());
        itemThemeStyleBg.setImageDrawable(new ColorDrawable(themeEntity.getBgColor()));

        if (themeEntity.getBgColor() == -1) {
            itemThemeStyleDayText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleMonthText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleWeekText.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleContentText.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
            itemThemeStyleDayLuckyText.setTextColor(ContextCompat.getColor(mContext, R.color.color85E9E6));
            itemThemeStyleDayLucky.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
            itemThemeStyleDayBad.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
        } else {
            itemThemeStyleDayText.setTextColor(themeEntity.getDayColor());
            itemThemeStyleWeekText.setTextColor(themeEntity.getWeekColor());
            itemThemeStyleMonthText.setTextColor(themeEntity.getMonthColor());
            itemThemeStyleContentText.setTextColor(themeEntity.getTextColor());
            itemThemeStyleDayLuckyText.setTextColor(themeEntity.getLuckyColor());
            itemThemeStyleDayBadText.setTextColor(themeEntity.getBadColor());
            itemThemeStyleDayLucky.setTextColor(Color.WHITE);
            itemThemeStyleDayBad.setTextColor(Color.WHITE);
        }

        itemThemeStyleDayText.setTextColor(themeEntity.getDayColor());
        itemThemeStyleWeekText.setTextColor(themeEntity.getWeekColor());
        itemThemeStyleMonthText.setTextColor(themeEntity.getMonthColor());

        /**
         * set list item showing color square
         */
        settingStyleEntityList.get(0).setColor(themeEntity.getBgColor());
        settingStyleEntityList.get(1).setColor(themeEntity.getDayColor());
        settingStyleEntityList.get(2).setColor(themeEntity.getWeekColor());
        settingStyleEntityList.get(3).setColor(themeEntity.getMonthColor());
        settingStyleEntityList.get(4).setColor(themeEntity.getTextColor());
        settingStyleEntityList.get(5).setColor(themeEntity.getLuckyColor());
        settingStyleEntityList.get(6).setColor(themeEntity.getBadColor());
        settingStyleAdapter.notifyDataSetChanged(settingStyleEntityList);

        UpdateAppWidget.getInstance().updateAppWidget();

    }
}
