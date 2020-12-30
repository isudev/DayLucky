package com.isuu.base.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * Description RxBusSubscriber RxBus use Subscriber get next try , catch
 * Created by wangisu@qq.com on 2019/7/15.
 */
public abstract class RxBusSubscriber<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e.getMessage());
        e.printStackTrace();
    }

    public abstract void onEvent(T t);

    public abstract void onFailure(String errMsg);
}