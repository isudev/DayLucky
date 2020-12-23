package com.zaotao.daylucky.view.vip;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.contract.DayLuckVipContract;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.presenter.DayLuckVipPresenter;
import com.zaotao.daylucky.view.adapter.VipLuckyContentAdapter;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;
import com.zaotao.daylucky.widget.dialog.DialogUnlockedVip;
import com.zaotao.daylucky.widget.ringview.RingChartView;

import java.util.List;

import butterknife.BindView;

/**
 * Description VipMonthFragment
 * Created by wangisu@qq.com on 12/22/20.
 */
public class VipMonthFragment extends BaseFragment<DayLuckVipPresenter> implements DayLuckVipContract.View {

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
    RingChartView vipMonthChartRing1;
    @BindView(R.id.vip_month_chart_ring2)
    RingChartView vipMonthChartRing2;
    @BindView(R.id.vip_month_chart_ring3)
    RingChartView vipMonthChartRing3;
    @BindView(R.id.vip_month_chart_ring_text1)
    AppFakeBoldTextView vipMonthChartRingText1;
    @BindView(R.id.vip_month_chart_ring_text2)
    AppFakeBoldTextView vipMonthChartRingText2;
    @BindView(R.id.vip_month_chart_ring_text3)
    AppFakeBoldTextView vipMonthChartRingText3;
    @BindView(R.id.vip_lock_button_text)
    TextView vipLockButtonText;

    private DialogUnlockedVip dialogUnlockedVip;

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
    protected DayLuckVipPresenter initPresenter() {
        return new DayLuckVipPresenter();
    }

    @Override
    protected void initViewData(View view) {
        dialogUnlockedVip = new DialogUnlockedVip(mContext);
        vipLockButtonText.setText(R.string.vip_month_lock_text);
        LuckyVipEntity luckyVipMonthData = (LuckyVipEntity) getArguments().getSerializable("fragment_lucky_vip_month");
        luckyVipCount.setText(luckyVipMonthData.getMonth().getCont());
        String monthDate = luckyVipMonthData.getDate().getYear() + "." + luckyVipMonthData.getDate().getMonth();
        luckyVipMonthText0.setText(monthDate);
        luckyVipMonthText1.setText(monthDate);
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
        /**
         * set bottom recycler view data
         */
        List<FortuneContentEntity> fortuneContentEntities = getSupportPresenter().initMonthFortuneList(luckyVipMonthData);
        if (fortuneContentEntities.size() == 0) {
            luckyVipContent.setVisibility(View.GONE);
            vipMonthLockView.setVisibility(View.VISIBLE);
        } else {
            luckyVipContent.setVisibility(View.VISIBLE);
            vipMonthLockView.setVisibility(View.GONE);
            luckyVipContent.setLayoutManager(new LinearLayoutManager(mContext));
            VipLuckyContentAdapter vipLuckyContentAdapter = new VipLuckyContentAdapter(mContext);
            luckyVipContent.setAdapter(vipLuckyContentAdapter);
            vipLuckyContentAdapter.notifyDataSetChanged(fortuneContentEntities);
        }
        /**
         * register constellation index
         */
        getSupportPresenter().registerSelectPosition();
        /**
         * order pay action
         */
        vipMonthLockButton.setOnClickListener(v -> dialogUnlockedVip.showDialog((mobile, payType) -> {
            switch (payType){
                case 0:
                    getSupportPresenter().aliPayOrder(mActivity, luckyVipMonthData.getMonth().getId(), mobile);
                    break;
                case 1:
                    getSupportPresenter().weChatPayOrder(mActivity,luckyVipMonthData.getMonth().getId(),mobile);
                    break;
            }
        }));
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onSuccessLucky(LuckyVipEntity luckyVipEntity) {

    }

    @Override
    public void onChangeConstellationIndex(int index) {
        dialogUnlockedVip.setSelectText(Constants.CONSTELLATION_DESC[index]);
    }

    @Override
    public void onSuccessOrderPay() {
        dialogUnlockedVip.dismiss();
    }
}
