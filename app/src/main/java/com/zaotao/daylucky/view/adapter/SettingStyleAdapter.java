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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.base.view.CircleImageView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.entity.SettingStyleEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;

import java.util.List;


/**
 * Description SettingStyleAdapter
 * Created by wangisu@qq.com on 2020/10/23
 */
public class SettingStyleAdapter extends RecyclerView.Adapter<SettingStyleAdapter.ViewHolder> {

    private Context context;
    private List<SettingStyleEntity> items;

    private OnItemPositionClickListener onItemPositionClickListener;


    public void setOnItemPositionClickListener(OnItemPositionClickListener onItemPositionClickListener) {
        this.onItemPositionClickListener = onItemPositionClickListener;
    }

    public SettingStyleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SettingStyleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting_style_view, parent, false);
        return new SettingStyleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingStyleAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemSettingStyleTextView;
        private CircleImageView itemSettingStyleImageView;

        ViewHolder(View view) {
            super(view);
            itemSettingStyleTextView = view.findViewById(R.id.item_setting_style_text_view);
            itemSettingStyleImageView = view.findViewById(R.id.item_setting_style_image_view);
        }

        public void bind(int position) {
            itemSettingStyleTextView.setText(items.get(position).getName());
            itemSettingStyleImageView.setImageDrawable(new ColorDrawable(items.get(position).getColor()));
            itemView.setOnClickListener(v -> onItemPositionClickListener.onClick(position));
        }
    }

    public void notifyDataSetChanged(List<SettingStyleEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}