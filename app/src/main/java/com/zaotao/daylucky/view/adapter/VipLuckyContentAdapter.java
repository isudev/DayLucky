package com.zaotao.daylucky.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.ColorManager;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.module.entity.FortuneContentEntity;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;

import java.util.List;


/**
 * Description LuckyItemAdapter
 * Created by wangisu@qq.com on 2020/10/23
 */
public class VipLuckyContentAdapter extends RecyclerView.Adapter<VipLuckyContentAdapter.ViewHolder> {

    private Context context;
    private List<FortuneContentEntity> items;

    private OnItemPositionClickListener onItemPositionClickListener;


    public void setOnItemPositionClickListener(OnItemPositionClickListener onItemPositionClickListener) {
        this.onItemPositionClickListener = onItemPositionClickListener;
    }

    public VipLuckyContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public VipLuckyContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vip_lucky_view, parent, false);
        return new VipLuckyContentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VipLuckyContentAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemVipLuckyViewTitleImage;
        private AppFakeBoldTextView itemVipLuckyTitleText;
        private ImageView itemVipLuckyViewLine;
        private TextView itemVipLuckyViewContent;

        ViewHolder(View view) {
            super(view);

            itemVipLuckyViewTitleImage = view.findViewById(R.id.item_vip_lucky_view_title_image);
            itemVipLuckyTitleText = view.findViewById(R.id.item_vip_lucky_title_text);
            itemVipLuckyViewLine = view.findViewById(R.id.item_vip_lucky_view_line);
            itemVipLuckyViewContent = view.findViewById(R.id.item_vip_lucky_view_content);


        }

        public void bind(int position) {
            FortuneContentEntity fortuneContentEntity = items.get(position);
            itemVipLuckyTitleText.setTextColor(ColorManager.FORTUNE_VIP_TITLE_TEXT_COLOR[position]);
            itemVipLuckyViewLine.setBackgroundColor(ColorManager.FORTUNE_VIP_TITLE_LINE_COLOR[position]);
            itemVipLuckyTitleText.setText(Constants.FORTUNE_DESC[position]);
            itemVipLuckyViewTitleImage.setImageResource(Constants.FORTUNE_VIP_IMG[position]);

            itemVipLuckyViewContent.setText(fortuneContentEntity.getText());
        }
    }

    public void notifyDataSetChanged(List<FortuneContentEntity> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}