package com.zaotao.daylucky.base;

/**
 * Description BaseSimplePresenter Manager
 * Created by wangisu@qq.com on 2019/7/15.
 */
public interface BaseSimplePresenter<T extends IView> {
    /**
     * 绑定Presenter
     */
    void attachView(T view);

    /**
     * 解绑Presenter
     */
    void detachView();

    /**
     * Presenter创建回调
     */
    void onPresenterCreated();

    /**
     * Presenter销毁回调
     */
    void onPresenterDestroy();


}
