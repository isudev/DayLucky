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
import com.zaotao.daylucky.app.DateUtils;
import com.zaotao.daylucky.module.entity.LuckyWeekEntity;
import com.zaotao.daylucky.widget.progress.VerticalProgressBar;

import java.util.List;

/**
 * Description LuckyWeekLineAdapter
 * Created by wangisu@qq.com on 2020-01-10
 */
public class LuckyWeekLineAdapter extends RecyclerView.Adapter<LuckyWeekLineAdapter.ViewHolder> {

    private Context context;
    private List<LuckyWeekEntity> items;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public LuckyWeekLineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LuckyWeekLineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lucky_week_line_view, parent, false);
        return new LuckyWeekLineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LuckyWeekLineAdapter.ViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private VerticalProgressBar itemLuckyWeekLineProgressView;
        private TextView itemLuckyWeekLineTextView;

        ViewHolder(View itemView) {
            super(itemView);

            itemLuckyWeekLineProgressView = itemView.findViewById(R.id.item_lucky_week_line_progress_view);
            itemLuckyWeekLineTextView = itemView.findViewById(R.id.item_lucky_week_line_text_view);

        }

        public void bind(int position) {
            itemLuckyWeekLineTextView.setText(DateUtils.WEEK_TIMES[position]);
            itemLuckyWeekLineProgressView.setProgress(items.get(position).getY());
            itemLuckyWeekLineProgressView.setFinishColor(ContextCompat.getColor(context, R.color.color00D1CB));
            itemLuckyWeekLineTextView.setTextColor(ContextCompat.getColor(context, items.get(position).getX().equals("今天") ? R.color.color00D1CB : R.color.color666666));
            itemView.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            });
        }
    }

    public void notifyDataSetChanged(List<LuckyWeekEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}