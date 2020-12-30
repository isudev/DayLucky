package com.zaotao.daylucky.presenter;

import android.content.Context;
import android.webkit.JavascriptInterface;

import androidx.appcompat.app.AppCompatActivity;

import com.isuu.base.utils.ToastUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description WebViewPresenter
 * Created by wangisu@qq.com on 12/30/20.
 */
public class WebViewPresenter {

    private static final String TAG = "WebViewPresenter";
    private CompositeDisposable mCompositeDisposable;

    private Context context;
    private AppCompatActivity activity;

    public WebViewPresenter(AppCompatActivity activity, Context context) {
        this.context = context;
        this.activity = activity;
    }

    @JavascriptInterface
    public void jsTest() {
    }

    private void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void onDestroy() {
        unSubscribe();
    }

    private void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

}
