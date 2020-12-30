package com.zaotao.daylucky.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.isuu.base.view.CircleImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.app.AppDataManager;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckCoreContract;
import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.view.activity.SelectActivity;
import com.zaotao.indicator.MagicIndicator;
import com.zaotao.indicator.buildins.UIUtil;
import com.zaotao.indicator.buildins.commonnavigator.CommonNavigator;
import com.zaotao.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.zaotao.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.zaotao.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.zaotao.indicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.zaotao.indicator.buildins.commonnavigator.titles.SimplePagerBoldTitleView;

import butterknife.BindView;

public class LuckyFragment extends BaseFragment<DayLuckCorePresenter> implements DayLuckCoreContract.View {
    @BindView(R.id.fragment_lucky_text_date)
    TextView fragmentLuckyTextDate;
    @BindView(R.id.fragment_lucky_text1)
    TextView fragmentLuckyText1;
    @BindView(R.id.fragment_lucky_text2)
    TextView fragmentLuckyText2;
    @BindView(R.id.tab_fragment_lucky)
    MagicIndicator tabFragmentLucky;
    @BindView(R.id.fragment_lucky_text3)
    TextView fragmentLuckyText3;
    @BindView(R.id.fragment_lucky_middle_text0)
    TextView fragmentLuckyMiddleText0;
    @BindView(R.id.fragment_lucky_middle_text1)
    TextView fragmentLuckyMiddleText1;
    @BindView(R.id.fragment_lucky_middle_text2)
    TextView fragmentLuckyMiddleText2;
    @BindView(R.id.fragment_lucky_image_click)
    CircleImageView fragmentLuckyImageClick;

    private FortuneWeekFragment fortuneWeekFragment;
    private FortuneMonthFragment fortuneMonthFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lucky;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {
        getSupportPresenter().initHomeLucky();

        getSupportPresenter().initHomeLucky(AppDataManager.getInstance().getSelectConstellationIndex());

        getSupportPresenter().registerSelectPosition(fragmentLuckyImageClick);

    }

    @Override
    protected void initListener() {
        fragmentLuckyImageClick.setOnClickListener(v -> startActivity(new Intent(mContext, SelectActivity.class)));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessLucky(LuckyEntity luckyEntity) {
        String dataText = DateUtils.formatDayText(luckyEntity.getDate().getTime()) + " "
                + DateUtils.formatMonthText(luckyEntity.getDate().getTime()) + " "
                + DateUtils.formatWeekText(luckyEntity.getDate().getTime());
        fragmentLuckyTextDate.setText(dataText);
        fragmentLuckyImageClick.setImageResource(AppDataManager.getInstance().getImageRes());

        fragmentLuckyText1.setText(luckyEntity.getToday().getCont());
        fragmentLuckyText2.setText(luckyEntity.getToday().getTitle());
        fragmentLuckyText3.setText(luckyEntity.getToday().getLuck_desc());

        fragmentLuckyMiddleText0.setText(luckyEntity.getToday().getLuck());
        fragmentLuckyMiddleText1.setText(luckyEntity.getToday().getBad());
        fragmentLuckyMiddleText2.setText(luckyEntity.getToday().getToday_advice());
        fortuneWeekFragment = FortuneWeekFragment.newInstance(luckyEntity);
        fortuneMonthFragment = FortuneMonthFragment.newInstance(luckyEntity);
        loadTabFragment(0);
        initIndicator();
    }

    @Override
    public void onSuccessThemeInfo(ThemeEntity themeEntity) {

    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return getSupportPresenter().initTitles(mContext).length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerBoldTitleView simplePagerTitleView = new SimplePagerBoldTitleView(mContext);
                int padding = UIUtil.dip2px(context, 15);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setSingleLine();
                simplePagerTitleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                simplePagerTitleView.setText(getSupportPresenter().initTitles(mContext)[index]);
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.color999999));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.color191919));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        viewPagerFragmentLucky.setCurrentItem(index);
                        tabFragmentLucky.onPageSelected(index);
                        tabFragmentLucky.onPageScrolled(index, 0.0F, 0);

                        loadTabFragment(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.6f));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setYOffset(UIUtil.dip2px(context, 5));
                indicator.setLineWidth(UIUtil.dip2px(context, 33));
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setColors(ContextCompat.getColor(mContext, R.color.color85E9E6));
                return indicator;
            }
        });
        tabFragmentLucky.setNavigator(commonNavigator);
    }


    public void loadTabFragment(int index) {
        if (index == 0) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_fragment_lucky, fortuneWeekFragment, FortuneWeekFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        } else if (index == 1) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_fragment_lucky, fortuneMonthFragment, FortuneMonthFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }
}
