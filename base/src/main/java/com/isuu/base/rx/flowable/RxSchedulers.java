package com.isuu.base.rx.flowable;

import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description RxSchedulers LifecycleProvider Transformer
 * Created by wangisu@qq.com on 2019/7/15.
 */
public class RxSchedulers {

    public static <T>FlowableTransformer<T,T> applySchedulers(final LifecycleProvider provider){
        return upstream -> upstream
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxSchedulers.<T>bindToLifecycle(provider));
    }



    public static <T> FlowableTransformer<T, T> applySchedulers(final LifecycleProvider provider, final ActivityEvent event) {
        return upstream -> upstream
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxSchedulers.<T>bindToLifecycle(provider,event));
    }

    public static <T> FlowableTransformer<T, T> applySchedulers(final LifecycleProvider provider, final FragmentEvent event) {
        return upstream -> upstream
                .retryWhen(new RetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxSchedulers.<T>bindToLifecycle(provider,event));
    }



    private static <T> LifecycleTransformer<T> bindToLifecycle(LifecycleProvider provider) {
        if (provider instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) provider).bindToLifecycle();
        } else if (provider instanceof RxFragment) {
            return ((RxFragment) provider).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("class must extents RxAppCompatActivity or RxFragment");
        }
    }

    private static <T> LifecycleTransformer<T> bindToLifecycle(LifecycleProvider provider, ActivityEvent event) {
        if (provider instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) provider).bindUntilEvent(event);
        } else {
            throw new IllegalArgumentException("class must extents RxAppCompatActivity");
        }
    }

    private static <T> LifecycleTransformer<T> bindToLifecycle(LifecycleProvider provider, FragmentEvent event) {
        if (provider instanceof RxFragment) {
            return ((RxFragment) provider).bindUntilEvent(event);
        } else {
            throw new IllegalArgumentException("class must extents RxFragment");
        }
    }

}