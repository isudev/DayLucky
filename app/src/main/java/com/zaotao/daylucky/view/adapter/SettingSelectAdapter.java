package com.zaotao.daylucky.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.entity.SettingSelectEntity;
import com.zaotao.daylucky.module.entity.ThemeEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;

import java.util.List;


/**
 * Description SettingSelectAdapter
 * Created by wangisu@qq.com on 2020/10/23
 */
public class SettingSelectAdapter extends RecyclerView.Adapter<SettingSelectAdapter.ViewHolder> {

    private Context context;
    private List<SettingSelectEntity> items;

    private OnItemPositionClickListener onItemPositionClickListener;


    public void setOnItemPositionClickListener(OnItemPositionClickListener onItemPositionClickListener) {
        this.onItemPositionClickListener = onItemPositionClickListener;
    }

    public SettingSelectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SettingSelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_setting_select_view, parent, false);
        return new SettingSelectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SettingSelectAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemSettingSelectImageView;
        private TextView itemSettingSelectTextView;


        ViewHolder(View view) {
            super(view);
            itemSettingSelectImageView = view.findViewById(R.id.item_setting_select_image_view);
            itemSettingSelectTextView = view.findViewById(R.id.item_setting_select_text_view);
        }

        public void bind(int position) {
            itemSettingSelectImageView.setImageResource(items.get(position).getImg());
            itemSettingSelectTextView.setText(items.get(position).getName());

            itemView.setOnClickListener(v -> onItemPositionClickListener.onClick(position));
        }
    }

    public void notifyDataSetChanged(List<SettingSelectEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}