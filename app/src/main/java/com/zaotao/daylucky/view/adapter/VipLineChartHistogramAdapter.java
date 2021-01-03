package com.zaotao.daylucky.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.module.entity.LuckyTodayEntity;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;

import java.util.List;

/**
 * Description VipLineChartHistogramAdapter
 * Created by wangisu@qq.com on 2020-01-10
 */
public class VipLineChartHistogramAdapter extends RecyclerView.Adapter<VipLineChartHistogramAdapter.ViewHolder> {

    private Context context;
    private List<LuckyTodayEntity> items;
    private int todayPosition;

    public VipLineChartHistogramAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VipLineChartHistogramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vip_line_chart_histogram, parent, false);
        return new VipLineChartHistogramAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VipLineChartHistogramAdapter.ViewHolder holder, int position) {
        holder.bind(position);


    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemHomeFortuneLineChartXImage;
        private AppFakeBoldTextView itemHomeFortuneLineChartXText;

        ViewHolder(View view) {
            super(view);
            itemHomeFortuneLineChartXImage = view.findViewById(R.id.item_home_fortune_line_chart_x_image);
            itemHomeFortuneLineChartXText = view.findViewById(R.id.item_home_fortune_line_chart_x_text);
        }

        public void bind(int position) {
            if (items.get(position).getX().equals(context.getString(R.string.today))) {
                todayPosition = position;
                itemHomeFortuneLineChartXImage.setVisibility(View.VISIBLE);
                itemHomeFortuneLineChartXImage.setImageResource(R.drawable.ic_line_chart_fill_bg);
                itemHomeFortuneLineChartXText.setTextColor(ContextCompat.getColor(context, R.color.color6983FE));
            } else {
                itemHomeFortuneLineChartXImage.setVisibility(View.INVISIBLE);
                itemHomeFortuneLineChartXText.setTextColor(ContextCompat.getColor(context, R.color.color666666));
            }
            if (items.size() == 7) {
                itemHomeFortuneLineChartXText.setText(DateUtils.WEEK_TIMES[position]);
            } else {
                itemHomeFortuneLineChartXText.setText(DateUtils.YEAR_TIMES[position]);
                itemHomeFortuneLineChartXText.setRotation(-45);
            }
        }
    }

    public void notifyDataSetChanged(List<LuckyTodayEntity> items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getX().equals(context.getString(R.string.today))) {
                todayPosition = i;
            }
        }
        notifyDataSetChanged();
    }

    public int getTodayPosition() {
        return todayPosition;
    }

}