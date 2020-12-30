package com.zaotao.daylucky.base;

import com.isuu.base.rx.RxBus;
import com.trello.rxlifecycle3.LifecycleProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description BasePresenter
 * Created by wangisu@qq.com on 2019/7/15.
 */
public abstract class BasePresenter<T extends IView> implements BaseSimplePresenter<T> {
    private T mView;
    protected CompositeDisposable mCompositeDisposable;

    public T getView() {
        return mView;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 解绑Presenter
     */
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    /**
     * 添加RxJava订阅
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 取消RxJava订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    /**
     * post event
     * @param o Object event
     */
    protected void sendEvent(Object o){
        RxBus.getDefault().post(o);
    }

    /**
     * post sticky event
     * @param o Object event
     */
    protected void sendEventSticky(Object o){
        RxBus.getDefault().postSticky(o);
    }

    protected LifecycleProvider getLifecycleProvider() {
        LifecycleProvider provider = null;
        if (null != mView && mView instanceof LifecycleProvider) {
            provider = (LifecycleProvider) mView;
        }
        return provider;
    }

}
