package com.zaotao.daylucky.widget.navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.module.listener.OnItemPositionClickListener;


/**
 * Description BottomNavigationView
 * Created by wangisu@qq.com on 2020/10/21
 */
public class BottomNavigationView extends LinearLayout {
    private RelativeLayout mainBottomBarParent0;
    private ImageView mainBottomBarAnimation0;
    private TextView mainBottomBarText0;
    private RelativeLayout mainBottomBarParent1;
    private ImageView mainBottomBarAnimation1;
    private TextView mainBottomBarText1;
    private RelativeLayout mainBottomBarParent2;
    private ImageView mainBottomBarAnimation2;
    private TextView mainBottomBarText2;

    public static final int TAB_LUCKY = 0;
    public static final int TAB_THEME = 1;
    public static final int TAB_STYLE = 2;
    private Context mContext;

    private OnItemPositionClickListener onItemPositionClickListener;

    public void setOnItemPositionClickListener(OnItemPositionClickListener onItemPositionClickListener) {
        this.onItemPositionClickListener = onItemPositionClickListener;
    }

    public BottomNavigationView(Context context) {
        super(context);
        init(context);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.app_bottom_navigation_view, this);
        mainBottomBarParent0 = view.findViewById(R.id.main_bottom_bar_parent0);
        mainBottomBarAnimation0 = view.findViewById(R.id.main_bottom_bar_animation0);
        mainBottomBarText0 = view.findViewById(R.id.main_bottom_bar_text0);
        mainBottomBarParent1 = view.findViewById(R.id.main_bottom_bar_parent1);
        mainBottomBarAnimation1 = view.findViewById(R.id.main_bottom_bar_animation1);
        mainBottomBarText1 = view.findViewById(R.id.main_bottom_bar_text1);
        mainBottomBarParent2 = view.findViewById(R.id.main_bottom_bar_parent2);
        mainBottomBarAnimation2 = view.findViewById(R.id.main_bottom_bar_animation2);
        mainBottomBarText2 = view.findViewById(R.id.main_bottom_bar_text2);


        mainBottomBarParent0.setOnClickListener(v -> {
            showBottomView(TAB_LUCKY);
            onItemPositionClickListener.onClick(TAB_LUCKY);
        });

        mainBottomBarParent1.setOnClickListener(v -> {
            showBottomView(TAB_THEME);
            onItemPositionClickListener.onClick(TAB_THEME);

        });

        mainBottomBarParent2.setOnClickListener(v -> {
            showBottomView(TAB_STYLE);
            onItemPositionClickListener.onClick(TAB_STYLE);
        });



        initBottomTabs(TAB_LUCKY);
    }

    private void initBottomTabs(int position) {
        mainBottomBarAnimation0.setImageResource(R.drawable.ic_main_bottom_image_unselect0);
        mainBottomBarAnimation1.setImageResource(R.drawable.ic_main_bottom_image_unselect1);
        mainBottomBarAnimation2.setImageResource(R.drawable.ic_main_bottom_image_unselect2);

        mainBottomBarText0.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
        mainBottomBarText1.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
        mainBottomBarText2.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));

        if (position == TAB_LUCKY) {
            mainBottomBarAnimation0.setImageResource(R.drawable.ic_main_bottom_image_select0);
            mainBottomBarText0.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
        } else if (position == TAB_THEME) {
            mainBottomBarAnimation1.setImageResource(R.drawable.ic_main_bottom_image_select1);
            mainBottomBarText1.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
        }else if (position == TAB_STYLE) {
            mainBottomBarAnimation2.setImageResource(R.drawable.ic_main_bottom_image_select2);
            mainBottomBarText2.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
        }
    }

    public void showBottomView(int position) {
        mainBottomBarAnimation0.setImageResource(R.drawable.ic_main_bottom_image_unselect0);
        mainBottomBarText0.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
        mainBottomBarText0.setTypeface(Typeface.DEFAULT);

        mainBottomBarAnimation1.setImageResource(R.drawable.ic_main_bottom_image_unselect1);
        mainBottomBarText1.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
        mainBottomBarText1.setTypeface(Typeface.DEFAULT);

        mainBottomBarAnimation2.setImageResource(R.drawable.ic_main_bottom_image_unselect2);
        mainBottomBarText2.setTextColor(ContextCompat.getColor(mContext, R.color.color909094));
        mainBottomBarText2.setTypeface(Typeface.DEFAULT);



        if (position == TAB_LUCKY) {
            mainBottomBarAnimation0.setImageResource(R.drawable.ic_main_bottom_image_select0);
            mainBottomBarText0.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
            mainBottomBarText0.getPaint().setFakeBoldText(true);
        } else if (position == TAB_THEME) {
            mainBottomBarAnimation1.setImageResource(R.drawable.ic_main_bottom_image_select1);
            mainBottomBarText1.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
            mainBottomBarText1.getPaint().setFakeBoldText(true);
        } else if (position == TAB_STYLE) {
            mainBottomBarAnimation2.setImageResource(R.drawable.ic_main_bottom_image_select2);
            mainBottomBarText2.setTextColor(ContextCompat.getColor(mContext, R.color.color23242D));
            mainBottomBarText2.getPaint().setFakeBoldText(true);
        }

    }
}