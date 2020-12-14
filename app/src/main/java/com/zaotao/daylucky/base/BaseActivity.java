package com.zaotao.daylucky.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.umeng.message.PushAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <p>
 * Base class for activities that use the
 * <a href="{@docRoot}tools/extras/support-library.html">support library</a> action bar features.
 * <p>
 * <p>You can add an {@link androidx.appcompat.app.ActionBar} to your activity when running on API level 7 or higher
 * by extending this class for your activity and setting the activity theme to
 * {@link androidx.appcompat.R.style#Theme_AppCompat Theme.AppCompat} or a similar theme.
 * <p>
 * <div class="special reference">
 * <h3>Developer Guides</h3>
 * <p>
 * <p>For information about how to use the action bar, including how to add action items, navigation
 * modes and more, read the <a href="{@docRoot}guide/topics/ui/actionbar.html">Action
 * Bar</a> API guide.</p>
 * </div>
 * <p>
 * Description BaseSimpleActivity extends RxAppCompatActivity
 * Created by wangisu@qq.com on 2019/7/15.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IView {
    protected Context mContext;
    protected RxAppCompatActivity mActivity;
    private Unbinder mUnBinder;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setGreyscaleTheme();
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.onPresenterCreated();
        }
        //this context
        mContext = this;
        mActivity = this;
        //transparent status bar
        setStatusBarFullTransparent();
        //set NativeLightStatusBar
        setAndroidNativeLightStatusBar(mActivity, true);
        //set layout
        setContentView(getLayoutId());
        //ButterKnife
        mUnBinder = ButterKnife.bind(this);
        //初始化状态与数据
        initCreate(savedInstanceState);
        //初始化监听
        initListener();
        //该方法是【友盟+】Push后台进行日活统计及多维度推送的必调用方法
        PushAgent.getInstance(mContext).onAppStart();
    }

    /**
     * fix Caused by: java.lang.IllegalStateException: Only fullscreen opaque activities can request orientation
     * @param requestedOrientation
     */
    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        try {
            super.setRequestedOrientation(requestedOrientation);
        } catch (IllegalStateException e) {
// Only fullscreen activities can request orientation
            e.printStackTrace();
        }
    }

    /**
     * [0.33,   0.59,   0.11,   0,  0]
     * [0.33,   0.59,   0.11,   0,  0]
     * [0.33,   0.59,   0.11,   0,  0]
     * [0.33,   0.59,   0.11,   0,  0]
     * [0,      0,      0,      1,  0]
     * <p>
     * [0.213,   0.715,   0.072,   0,  0]
     * [0.213,   0.715,   0.072,   0,  0]
     * [0.213,   0.715,   0.072,   0,  0]
     * [0.213,   0.715,   0.072,   0,  0]
     * [0,       0,       0,       1,  0]
     */
    private void setGreyscaleTheme() {
        // Create a paint object with 0 saturation greyscale
        ColorMatrix colorMatrix = new ColorMatrix();
        /**
         * Set the matrix to affect the saturation of colors.
         *
         * @param sat A value of 0 maps the color to gray-scale. 1 is identity.
         */
        colorMatrix.setSaturation(0);
        Paint greyscalePaint = new Paint();
        greyscalePaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // Create a hardware layer with the greyscale paint
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, greyscalePaint);
    }

    //change system status bar light
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    private void setStatusBarFullTransparent() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onPresenterDestroy();
            mPresenter.detachView();
        }
        mUnBinder.unbind();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initCreate(Bundle savedInstanceState);

    protected abstract void initListener();

    protected abstract T initPresenter();

    public T getSupportPresenter() {
        return mPresenter;
    }

}
