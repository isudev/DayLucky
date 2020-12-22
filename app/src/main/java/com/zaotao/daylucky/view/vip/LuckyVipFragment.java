package com.zaotao.daylucky.view.vip;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.zaotao.base.view.CircleImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.LuckDataManager;
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

        getSupportPresenter().initHomeLucky(LuckDataManager.getInstance().getSelectConstellationIndex(), "15510062339");

    }

    @Override
    protected void initListener() {
    }


    @Override
    public void onSuccessLucky(LuckyVipEntity luckyVipEntity) {
        vipConstellationImage.setImageResource(LuckDataManager.getInstance().getImageRes());
        vipConstellationText.setText(Constants.CONSTELLATION_DESC[LuckDataManager.getInstance().getSelectConstellationIndex()]);
        vipWeekFragment = VipWeekFragment.newInstance(luckyVipEntity);
        vipMonthFragment = VipMonthFragment.newInstance(luckyVipEntity);
        vipYearFragment = VipYearFragment.newInstance(luckyVipEntity);

        loadTabFragment(0);
        initIndicator();
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
                indicator.setColors(ContextCompat.getColor(mContext, R.color.color85E9E6));
                return indicator;
            }
        });
        tabVipLucky.setNavigator(commonNavigator);
    }


    public void loadTabFragment(int index) {
        if (index == 0) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipWeekFragment, VipWeekFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        } else if (index == 1) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipMonthFragment, VipMonthFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        } else if (index == 2) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.view_pager_vip_lucky, vipYearFragment, VipYearFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }
}
