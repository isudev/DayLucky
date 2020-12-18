package com.zaotao.daylucky.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.entity.LuckyWeekEntity;
import com.zaotao.daylucky.widget.progress.VerticalProgressBar;

import java.util.List;

/**
 * Description HomeFortuneLineChartXAdapter
 * Created by wangisu@qq.com on 2020-01-10
 */
public class HomeFortuneWeekLineChartXAdapter extends RecyclerView.Adapter<HomeFortuneWeekLineChartXAdapter.ViewHolder> {

    private Context context;
    private List<LuckyWeekEntity> items;
    private String[] weekTimes;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HomeFortuneWeekLineChartXAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HomeFortuneWeekLineChartXAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_fortune_week_line_chart_x, parent, false);
        return new HomeFortuneWeekLineChartXAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeFortuneWeekLineChartXAdapter.ViewHolder holder, int position) {
        holder.itemHomeFortuneLineChartXText.setText(weekTimes[position]);
        holder.itemHomeFortuneLineVerticalProgressBar.setProgress(items.get(position).getY());
        holder.itemHomeFortuneLineVerticalProgressBar.setFinishColor(ContextCompat.getColor(context, R.color.color00D1CB));
        holder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.onClick(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemHomeFortuneLineChartXText;
        private VerticalProgressBar itemHomeFortuneLineVerticalProgressBar;

        ViewHolder(View view) {
            super(view);
            itemHomeFortuneLineChartXText = view.findViewById(R.id.item_home_fortune_line_chart_x_text);
            itemHomeFortuneLineVerticalProgressBar = view.findViewById(R.id.item_home_fortune_line_verticalProgressBar);
        }
    }

    public void notifyDataSetChanged(List<LuckyWeekEntity> items, String[] weekTimes) {
        this.items = items;
        this.weekTimes = weekTimes;
        notifyDataSetChanged();
    }

}