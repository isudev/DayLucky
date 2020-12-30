package com.zaotao.daylucky.view.vip;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;

import com.isuu.base.view.CircleImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.AppDataManager;
import com.zaotao.daylucky.app.StatisticsUtils;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckVipContract;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.presenter.DayLuckVipPresenter;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;
import com.zaotao.indicator.MagicIndicator;
import com.zaotao.indicator.buildins.UIUtil;
import com.zaotao.indicator.buildins.commonnavigator.CommonNavigator;
import com.zaotao.indicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.zaotao.indicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.zaotao.indicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.zaotao.indicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.zaotao.indicator.buildins.commonnavigator.titles.SimplePagerBoldTitleView;

import butterknife.BindView;

public class LuckyVipFragment extends BaseFragment<DayLuckVipPresenter> implements DayLuckVipContract.View {


    @BindView(R.id.vip_constellation_image)
    CircleImageView vipConstellationImage;
    @BindView(R.id.tab_vip_lucky)
    MagicIndicator tabVipLucky;
    @BindView(R.id.view_pager_vip_lucky)
    FrameLayout viewPagerVipLucky;
    @BindView(R.id.vip_constellation_text)
    AppFakeBoldTextView vipConstellationText;
    private String[] titles = {"本周运势", "本月运势", "本年运势"};
    private VipWeekFragment vipWeekFragment;
    private VipMonthFragment vipMonthFragment;
    private VipYearFragment vipYearFragment;
    private static final int NORMAL_INDEX = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lucky_vip;
    }

    @Override
    protected DayLuckVipPresenter initPresenter() {
        return new DayLuckVipPresenter();
    }

    @Override
    protected void initViewData(View view) {

        getSupportPresenter().registerSelectPosition();

        getSupportPresenter().initHomeLucky(AppDataManager.getInstance().getSelectConstellationIndex(), AppDataManager.getInstance().getVipMobile());

    }

    @Override
    protected void initListener() {
    }


    @Override
    public void onSuccessLucky(LuckyVipEntity luckyVipEntity) {
        vipConstellationImage.setImageResource(AppDataManager.getInstance().getImageRes());
        vipConstellationText.setText(Constants.CONSTELLATION_DESC[AppDataManager.getInstance().getSelectConstellationIndex()]);
        vipWeekFragment = VipWeekFragment.newInstance(luckyVipEntity);
        vipMonthFragment = VipMonthFragment.newInstance(luckyVipEntity);
        vipYearFragment = VipYearFragment.newInstance(luckyVipEntity);
        loadTabFragment(NORMAL_INDEX);
        initIndicator();
    }

    @Override
    public void onChangeConstellationIndex(int index) {
        getSupportPresenter().initHomeLucky(AppDataManager.getInstance().getSelectConstellationIndex(), AppDataManager.getInstance().getVipMobile());
    }

    @Override
    public void onSuccessOrderPay() {

    }


    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerBoldTitleView simplePagerTitleView = new SimplePagerBoldTitleView(mContext);
                int padding = UIUtil.dip2px(context, 15);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setSingleLine();
                simplePagerTitleView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.color999999));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.color191919));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabVipLucky.onPageSelected(index);
                        tabVipLucky.onPageScrolled(index, 0.0F, 0);

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
                indicator.setColors(ContextCompat.getColor(mContext, R.color.color7F96FE));
                return indicator;
            }
        });
        tabVipLucky.setNavigator(commonNavigator);
    }


    public void loadTabFragment(int index) {
        if (index == 0) {
            StatisticsUtils.onClickWeekPay(mContext);
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipWeekFragment, VipWeekFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        } else if (index == 1) {
            StatisticsUtils.onClickMonthPay(mContext);
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipMonthFragment, VipMonthFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        } else if (index == 2) {
            StatisticsUtils.onClickYearPay(mContext);
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipYearFragment, VipYearFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }
}
