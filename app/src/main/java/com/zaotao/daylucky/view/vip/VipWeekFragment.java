package com.zaotao.daylucky.view.vip;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseFragment;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.presenter.DayLuckVipPresenter;
import com.zaotao.daylucky.view.adapter.VipLineChartHistogramAdapter;
import com.zaotao.daylucky.view.adapter.VipLuckyContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description VipWeekYearFragment
 * Created by wangisu@qq.com on 12/22/20.
 */
public class VipWeekFragment extends BaseFragment<DayLuckVipPresenter> {

    @BindView(R.id.lucky_vip_line_chart)
    LineChart luckyVipLineChart;
    @BindView(R.id.lucky_vip_line_chart_items)
    RecyclerView luckyVipLineChartItems;
    @BindView(R.id.home_fortune_chart_line)
    RelativeLayout homeFortuneChartLine;
    @BindView(R.id.lucky_vip_content)
    RecyclerView luckyVipContent;
    @BindView(R.id.lucky_vip_week_text0)
    TextView luckyVipWeekText0;
    @BindView(R.id.lucky_vip_week_text1)
    TextView luckyVipWeekText1;
    @BindView(R.id.vip_week_lock_button)
    LinearLayout vipWeekLockButton;
    @BindView(R.id.vip_week_lock_view)
    RelativeLayout vipWeekLockView;

    private VipLineChartHistogramAdapter vipLineChartHistogramAdapter;
    private VipLuckyContentAdapter vipLuckyContentAdapter;

    public static VipWeekFragment newInstance(LuckyVipEntity luckyVipEntity) {
        Bundle args = new Bundle();
        args.putSerializable("fragment_lucky_vip_week_year", luckyVipEntity);
        VipWeekFragment fragment = new VipWeekFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lucky_vip_week_year;
    }

    @Override
    protected DayLuckVipPresenter initPresenter() {
        return new DayLuckVipPresenter();
    }

    @Override
    protected void initViewData(View view) {
        LuckyVipEntity luckyVipWeekData = (LuckyVipEntity) getArguments().getSerializable("fragment_lucky_vip_week_year");
        luckyVipWeekText0.setText(luckyVipWeekData.getDate().getWeek().split("/")[0]);
        luckyVipWeekText1.setText(luckyVipWeekData.getDate().getWeek().split("/")[0]);

        luckyVipLineChart.getDescription().setEnabled(false);
        luckyVipLineChart.setBackgroundColor(Color.WHITE);
        luckyVipLineChart.setTouchEnabled(false);
        luckyVipLineChart.setDragEnabled(false);
        luckyVipLineChart.setVisibility(View.INVISIBLE);
        XAxis xAxis = luckyVipLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        luckyVipLineChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = luckyVipLineChart.getAxisLeft();
        leftAxis.setLabelCount(6, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularity(20f);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setDrawLabels(false);

        Legend legend = luckyVipLineChart.getLegend();
        legend.setEnabled(false);
        Description description = new Description();
        description.setEnabled(false);

        /**
         * set data
         */
        vipLineChartHistogramAdapter = new VipLineChartHistogramAdapter(mContext);
        vipLineChartHistogramAdapter.notifyDataSetChanged(luckyVipWeekData.getWeek_charts());
        luckyVipLineChartItems.setLayoutManager(new GridLayoutManager(mContext, 7));
        luckyVipLineChartItems.setAdapter(vipLineChartHistogramAdapter);

        List<Integer> points = new ArrayList<>();
        for (LuckyTodayEntity luckyTodayEntity :
                luckyVipWeekData.getWeek_charts()) {
            points.add(luckyTodayEntity.getY());
        }
        setLineChartData(points);
        /**
         * set bottom recycler view data
         */
        List<FortuneContentEntity> fortuneContentEntities = getSupportPresenter().initVipWeekFortuneList(luckyVipWeekData);
        if (fortuneContentEntities.size() == 0) {
            luckyVipContent.setVisibility(View.GONE);
            vipWeekLockView.setVisibility(View.VISIBLE);
        } else {
            luckyVipContent.setVisibility(View.VISIBLE);
            vipWeekLockView.setVisibility(View.GONE);
            luckyVipContent.setLayoutManager(new LinearLayoutManager(mContext));
            vipLuckyContentAdapter = new VipLuckyContentAdapter(mContext);
            luckyVipContent.setAdapter(vipLuckyContentAdapter);
            vipLuckyContentAdapter.notifyDataSetChanged(fortuneContentEntities);
        }
    }

    private void setLineChartData(List<Integer> points) {
        List<Entry> valuesCompPoint = new ArrayList<>();
        List<Entry> valuesCompNormal = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if (i == vipLineChartHistogramAdapter.getTodayPosition()) {
                valuesCompPoint.add(new Entry(i * 10, points.get(i)));
            }
            valuesCompNormal.add(new Entry(i * 10, points.get(i)));
        }
        Drawable lineDataSetDrawable = ContextCompat.getDrawable(mContext, R.drawable.ic_line_chart_fill_bg);
        LineDataSet lineDataSetPointStyle = new LineDataSet(valuesCompPoint, "");
        lineDataSetPointStyle.setColor(ContextCompat.getColor(mContext, R.color.color6983FE));
        lineDataSetPointStyle.setDrawCircles(true);
        lineDataSetPointStyle.setCircleColor(Color.WHITE);
        lineDataSetPointStyle.setCircleRadius(5f);
        lineDataSetPointStyle.setCircleHoleRadius(4f);
        lineDataSetPointStyle.setCircleHoleColor(ContextCompat.getColor(mContext, R.color.color6983FE));
        lineDataSetPointStyle.setDrawValues(true);
        lineDataSetPointStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetPointStyle.setLineWidth(3.5f);
        lineDataSetPointStyle.setDrawFilled(false);
        lineDataSetPointStyle.setFillDrawable(lineDataSetDrawable);
        lineDataSetPointStyle.setValueTextColor(Color.WHITE);

        LineDataSet lineDataSetNormalStyle = new LineDataSet(valuesCompNormal, "");
        lineDataSetNormalStyle.setColor(ContextCompat.getColor(mContext, R.color.color6983FE));
        lineDataSetNormalStyle.setDrawCircles(false);
        lineDataSetNormalStyle.setDrawValues(false);
        lineDataSetNormalStyle.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSetNormalStyle.setLineWidth(3.5f);
        lineDataSetNormalStyle.setDrawFilled(false);
        lineDataSetNormalStyle.setFillDrawable(lineDataSetDrawable);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSetPointStyle);
        dataSets.add(lineDataSetNormalStyle);

        LineData lineData = new LineData(dataSets);

        luckyVipLineChart.setData(lineData);
        luckyVipLineChart.invalidate();
        luckyVipLineChart.setVisibility(View.VISIBLE);
    }


    @Override
    protected void initListener() {
        vipWeekLockButton.setOnClickListener(v -> {
            showToast("lock");
        });
    }
}
