package com.zaotao.daylucky.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description BaseSimpleFragment extends RxFragment
 * Created by wangisu@qq.com on 2019/7/15.
 *
 * Static library support version of the framework's {@link android.app.Fragment}.
 * Used to write apps that run on platforms prior to Android 3.0.  When running
 * on Android 3.0 or above, this implementation is still used; it does not try
 * to switch to the framework's implementation. See the framework {@link android.app.Fragment}
 * documentation for a class overview.
 *
 * <p>The main differences when using this support version instead of the framework version are:
 * <ul>
 *  <li>Your activity must extend {@link FragmentActivity}
 *  <li>You must releasemessage_call_friend_icon {@link FragmentActivity#getSupportFragmentManager} to get the
 *  {@link FragmentManager}
 * </ul>
 *
 * Description BaseFragment extends BaseSimpleFragment
 * Created by wangisu@qq.com on 2019/7/15.
 */

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements IView {

    private T mPresenter;

    private Unbinder unBinder;

    public BaseActivity mActivity;
    public Context mContext;

    private View mRootView;

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(Preconditions.checkNotNull(getLayoutId(), "LayoutRes Initialization Exception at " + this.getClass().getCanonicalName() + " getLayoutId is null or return 0"), container,false);

        }else{
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = null;
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.onPresenterCreated();
        }
        unBinder = ButterKnife.bind(this, view);
        initViewData(mRootView);
        initListener();
    }

    //将代理类通用行为抽出来
    @SuppressLint("RestrictedApi")
    public T getSupportPresenter() {
        Preconditions.checkNotNull(mPresenter, "initPresenter ExceptionInInitializerError at " + this.getClass().getCanonicalName() + " getSupportImpPresenter is null ");
        return mPresenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
            this.mContext = context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onPresenterDestroy();
        }
        if (unBinder != null) {
            unBinder.unbind();
        }

        // initLeakCanary();
    }


    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract T initPresenter();

    protected abstract void initViewData(View view);

    protected abstract void initListener();//事件监听在此方法中实现

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    public void finish(){
        mActivity.finish();
    }

}
