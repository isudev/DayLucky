package com.zaotao.daylucky.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.view.RoundImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;

import java.util.List;


/**
 * Description ThemeStyleAdapter
 * Created by wangisu@qq.com on 2020/10/23
 */
public class ThemeStyleAdapter extends RecyclerView.Adapter<ThemeStyleAdapter.ViewHolder> {

    private Context context;
    private List<ThemeEntity> items;

    private OnItemPositionClickListener onItemPositionClickListener;


    public void setOnItemPositionClickListener(OnItemPositionClickListener onItemPositionClickListener) {
        this.onItemPositionClickListener = onItemPositionClickListener;
    }

    public ThemeStyleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ThemeStyleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_theme_style_view, parent, false);
        return new ThemeStyleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ThemeStyleAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemThemeStyleTitle;
        private RoundImageView itemThemeStyleBg;
        private TextView itemThemeStyleDayText;
        private TextView itemThemeStyleMonthText;
        private TextView itemThemeStyleWeekText;
        private TextView itemThemeStyleContentText;
        private TextView itemThemeStyleDayLucky;
        private TextView itemThemeStyleDayBad;
        private TextView itemThemeStyleDayLuckyText;
        private TextView itemThemeStyleDayBadText;
        private ImageView itemThemeStyleLine;

        ViewHolder(View view) {
            super(view);

            itemThemeStyleTitle = view.findViewById(R.id.item_theme_style_title);
            itemThemeStyleBg = view.findViewById(R.id.item_theme_style_bg);
            itemThemeStyleDayText = view.findViewById(R.id.item_theme_style_day_text);
            itemThemeStyleMonthText = view.findViewById(R.id.item_theme_style_month_text);
            itemThemeStyleWeekText = view.findViewById(R.id.item_theme_style_week_text);
            itemThemeStyleContentText = view.findViewById(R.id.item_theme_style_content_text);
            itemThemeStyleDayLucky = view.findViewById(R.id.item_theme_style_day_lucky);
            itemThemeStyleDayBad = view.findViewById(R.id.item_theme_style_day_bad);
            itemThemeStyleDayLuckyText = view.findViewById(R.id.item_theme_style_day_lucky_text);
            itemThemeStyleDayBadText = view.findViewById(R.id.item_theme_style_day_bad_text);
            itemThemeStyleLine = view.findViewById(R.id.item_theme_style_line);

        }

        public void bind(int position) {
            itemThemeStyleTitle.setVisibility(position == 0 || position == 1 ? View.VISIBLE : View.GONE);
            itemThemeStyleTitle.setText(position == 0 ? context.getString(R.string.theme_style_normal) : context.getString(R.string.theme_style_other));
            ThemeEntity themeEntity = items.get(position);
            itemThemeStyleDayText.setText(themeEntity.getDay());
            itemThemeStyleMonthText.setText(themeEntity.getMonth());
            itemThemeStyleWeekText.setText(themeEntity.getWeek());
            itemThemeStyleContentText.setText(themeEntity.getText());
            itemThemeStyleDayLuckyText.setText(themeEntity.getLucky());
            itemThemeStyleDayBadText.setText(themeEntity.getBad());
            itemThemeStyleLine.setBackgroundColor(themeEntity.getLineColor());
            itemThemeStyleBg.setImageDrawable(new ColorDrawable(themeEntity.getBgColor()));
            if (position == 0) {
                itemThemeStyleDayText.setTextColor(ContextCompat.getColor(context, R.color.color333333));
                itemThemeStyleMonthText.setTextColor(ContextCompat.getColor(context, R.color.color333333));
                itemThemeStyleWeekText.setTextColor(ContextCompat.getColor(context, R.color.color333333));
                itemThemeStyleContentText.setTextColor(ContextCompat.getColor(context, R.color.color909094));
                itemThemeStyleDayLuckyText.setTextColor(ContextCompat.getColor(context, R.color.color85E9E6));
                itemThemeStyleDayLucky.setTextColor(ContextCompat.getColor(context, R.color.color333333));
                itemThemeStyleDayBad.setTextColor(ContextCompat.getColor(context, R.color.color333333));

            } else {

                itemThemeStyleDayText.setTextColor(Color.WHITE);
                itemThemeStyleMonthText.setTextColor(Color.WHITE);
                itemThemeStyleWeekText.setTextColor(Color.WHITE);
                itemThemeStyleContentText.setTextColor(Color.WHITE);
                itemThemeStyleDayLuckyText.setTextColor(Color.WHITE);
                itemThemeStyleDayLucky.setTextColor(Color.WHITE);
                itemThemeStyleDayBad.setTextColor(Color.WHITE);
            }
            itemThemeStyleDayBadText.setTextColor(themeEntity.getBadColor());

            itemView.setOnClickListener(v -> onItemPositionClickListener.onClick(position));
        }
    }

    public void notifyDataSetChanged(List<ThemeEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}