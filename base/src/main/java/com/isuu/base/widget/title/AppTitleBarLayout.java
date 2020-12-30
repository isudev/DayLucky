package com.isuu.base.widget.title;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.isuu.base.listener.OnAppBarEndViewListener;
import com.isuu.base.listener.OnAppBarStartViewListener;
import com.zaotao.base.R;


/**
 * Description AppTitleBar
 * Created by wangisu@qq.com on 2019-08-16
 */
public class AppTitleBarLayout extends RelativeLayout {

    private Context context;
    private RelativeLayout rootView;
    private AppCompatImageView appTitleBarLayoutStartImageView;
    private AppCompatTextView appTitleBarLayoutStartTextView;
    private AppCompatTextView appTitleBarLayoutTitleTextView;
    private AppCompatImageView appTitleBarLayoutEndImageView;
    private AppCompatTextView appTitleBarLayoutEndTextView;

    private OnAppBarStartViewListener onAppBarStartViewListener;
    private OnAppBarEndViewListener onAppBarEndViewListener;

    public void setOnAppBarEndViewListener(OnAppBarEndViewListener onAppBarEndViewListener) {
        this.onAppBarEndViewListener = onAppBarEndViewListener;
    }

    public void setOnAppBarStartViewListener(OnAppBarStartViewListener onAppBarStartViewListener) {
        this.onAppBarStartViewListener = onAppBarStartViewListener;
    }

    public AppTitleBarLayout(Context context) {
        super(context);
        init(context);
    }

    public AppTitleBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.app_title_bar, this);
        rootView = view.findViewById(R.id.app_title_bar_root_view);
        appTitleBarLayoutStartImageView = view.findViewById(R.id.app_title_bar_layout_start_image_view);
        appTitleBarLayoutStartTextView = view.findViewById(R.id.app_title_bar_layout_start_text_view);
        appTitleBarLayoutTitleTextView = view.findViewById(R.id.app_title_bar_layout_title_text_view);
        appTitleBarLayoutEndImageView = view.findViewById(R.id.app_title_bar_layout_end_image_view);
        appTitleBarLayoutEndTextView = view.findViewById(R.id.app_title_bar_layout_end_text_view);

        appTitleBarLayoutStartImageView.setOnClickListener(this::startViewListener);

        appTitleBarLayoutStartTextView.setOnClickListener(this::startViewListener);

        appTitleBarLayoutEndImageView.setOnClickListener(this::endViewListener);

        appTitleBarLayoutEndTextView.setOnClickListener(this::endViewListener);

        appTitleBarLayoutTitleTextView.getPaint().setFakeBoldText(true);
    }

    public void setBackgroundTransparent() {
        rootView.setBackgroundColor(Color.TRANSPARENT);
    }

    public void setBackgroundColor(@ColorRes int color) {
        rootView.setBackgroundColor(color);
    }

    public void setBackgroundResource(@DrawableRes int resId){
        rootView.setBackgroundResource(resId);
    }

    private void startViewListener(View view) {
        if (onAppBarStartViewListener != null) {
            onAppBarStartViewListener.onClick(view);
        } else {
            ((Activity) context).finish();
        }
    }

    private void endViewListener(View view) {
        if (onAppBarEndViewListener != null) {
            onAppBarEndViewListener.onClick(view);
        }
    }

    public void setTitleTextColor(@ColorInt int color){
        appTitleBarLayoutTitleTextView.setTextColor(color);
    }

    public void setTitleText(String text) {
        appTitleBarLayoutTitleTextView.setText(text);
    }

    public void setTitleText(@StringRes int id) {
        setTitleText(context.getResources().getText(id).toString());
    }

    public void setStartText(String text) {
        if (null == text || text.equals("")) {
            return;
        }
        appTitleBarLayoutStartImageView.setVisibility(INVISIBLE);
        appTitleBarLayoutStartTextView.setVisibility(VISIBLE);
        appTitleBarLayoutStartTextView.setText(text);
    }

    public void setStartText(@StringRes int id) {
        setStartText(context.getResources().getText(id).toString());
    }

    public void setStartImageResource(@DrawableRes int resId) {
        appTitleBarLayoutStartTextView.setVisibility(INVISIBLE);
        appTitleBarLayoutStartImageView.setVisibility(VISIBLE);
        appTitleBarLayoutStartImageView.setImageResource(resId);
    }
    public void setStartTextColor(@ColorInt int color){
        appTitleBarLayoutStartTextView.setTextColor(color);
    }
    public void setEndTextColor(@ColorInt int color){
        appTitleBarLayoutEndTextView.setTextColor(color);
    }

    public void setEndText(String text) {
        if (null == text || text.equals("")) {
            return;
        }
        appTitleBarLayoutEndImageView.setVisibility(INVISIBLE);
        appTitleBarLayoutEndTextView.setVisibility(VISIBLE);
        appTitleBarLayoutEndTextView.setText(text);
    }

    public void setEndText(@StringRes int id) {
        setEndText(context.getResources().getText(id).toString());
    }

    public void setEndImageResource(@DrawableRes int resId) {
        appTitleBarLayoutEndTextView.setVisibility(INVISIBLE);
        appTitleBarLayoutEndImageView.setVisibility(VISIBLE);
        appTitleBarLayoutEndImageView.setImageResource(resId);
    }

    public AppCompatImageView getAppTitleBarLayoutStartImageView() {
        return appTitleBarLayoutStartImageView;
    }

    public AppCompatTextView getAppTitleBarLayoutStartTextView() {
        return appTitleBarLayoutStartTextView;
    }

    public AppCompatTextView getAppTitleBarLayoutTitleTextView() {
        return appTitleBarLayoutTitleTextView;
    }

    public AppCompatImageView getAppTitleBarLayoutEndImageView() {
        return appTitleBarLayoutEndImageView;
    }

    public AppCompatTextView getAppTitleBarLayoutEndTextView() {
        return appTitleBarLayoutEndTextView;
    }
}
