package com.zaotao.daylucky.module.api;


import io.reactivex.observers.DisposableObserver;

/**
 * Description ApiSubscriber Base DisposableObserver
 * Created by wangisu@qq.com on 2019/7/15.
 */
public abstract class ApiSubscriber<T> extends DisposableObserver<T> {

    protected ApiSubscriber() {
    }


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e.getMessage());
    }

    /**
     * request success next onComplete
     * onError not just
     */
    @Override
    public void onComplete() {
    }


    /**
     * result success
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * result error
     *
     * @param errMsg error msg
     */
    public abstract void onFailure(String errMsg);

}
