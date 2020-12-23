package com.zaotao.daylucky.view.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.presenter.DayLuckCorePresenter;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;
import com.zaotao.daylucky.widget.ringview.RingChartFortuneView;

import java.util.List;

import butterknife.BindView;

/**
 * Description VipMonthFragment
 * Created by wangisu@qq.com on 12/22/20.
 */
public class VipMonthFragment extends BaseFragment<DayLuckCorePresenter> {

    @BindView(R.id.lucky_vip_month_text0)
    TextView luckyVipMonthText0;
    @BindView(R.id.lucky_vip_count)
    TextView luckyVipCount;
    @BindView(R.id.lucky_vip_month_text1)
    TextView luckyVipMonthText1;
    @BindView(R.id.lucky_vip_content)
    RecyclerView luckyVipContent;
    @BindView(R.id.vip_month_lock_button)
    LinearLayout vipMonthLockButton;
    @BindView(R.id.vip_month_lock_view)
    RelativeLayout vipMonthLockView;
    @BindView(R.id.vip_month_chart_ring1)
    RingChartFortuneView vipMonthChartRing1;
    @BindView(R.id.vip_month_chart_ring2)
    RingChartFortuneView vipMonthChartRing2;
    @BindView(R.id.vip_month_chart_ring3)
    RingChartFortuneView vipMonthChartRing3;
    @BindView(R.id.vip_month_chart_ring_text1)
    AppFakeBoldTextView vipMonthChartRingText1;
    @BindView(R.id.vip_month_chart_ring_text2)
    AppFakeBoldTextView vipMonthChartRingText2;
    @BindView(R.id.vip_month_chart_ring_text3)
    AppFakeBoldTextView vipMonthChartRingText3;

    public static VipMonthFragment newInstance(LuckyVipEntity luckyVipEntity) {
        Bundle args = new Bundle();
        args.putSerializable("fragment_lucky_vip_month", luckyVipEntity);
        VipMonthFragment fragment = new VipMonthFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lucky_vip_month;
    }

    @Override
    protected DayLuckCorePresenter initPresenter() {
        return new DayLuckCorePresenter();
    }

    @Override
    protected void initViewData(View view) {
        LuckyVipEntity luckyVipMonthData = (LuckyVipEntity) getArguments().getSerializable("fragment_lucky_vip_month");
        luckyVipCount.setText(luckyVipMonthData.getMonth().getCont());

        /**
         * init data ring view & text
         */
        int currentDay = DateUtils.formatCurrentDay(System.currentTimeMillis());
        if (currentDay >= 1 && currentDay <= 10) {
            vipMonthChartRingText3.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
        } else if (currentDay > 10 && currentDay < 20) {
            vipMonthChartRingText2.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
        } else {
            vipMonthChartRingText1.setTextColor(ContextCompat.getColor(mContext, R.color.color333333));
        }
        List<LuckyTodayEntity> monthCharts = luckyVipMonthData.getMonth_charts();
        vipMonthChartRing1.setProgressNum(ContextCompat.getColor(mContext, R.color.color6983FE), monthCharts.get(0).getY());
        vipMonthChartRing2.setProgressNum(ContextCompat.getColor(mContext, R.color.color6983FE), monthCharts.get(1).getY());
        vipMonthChartRing3.setProgressNum(ContextCompat.getColor(mContext, R.color.color6983FE), monthCharts.get(2).getY());
    }

    @Override
    protected void initListener() {

    }
}
