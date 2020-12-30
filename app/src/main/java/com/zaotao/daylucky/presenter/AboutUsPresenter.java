package com.zaotao.daylucky.presenter;

import com.zaotao.daylucky.base.BasePresenter;
import com.zaotao.daylucky.contract.AboutUsContract;

/**
 * Description AboutUsPresenter
 * Created by wangisu@qq.com on 12/30/20.
 */
public class AboutUsPresenter extends BasePresenter<AboutUsContract.View> implements AboutUsContract.Presenter {
    @Override
    public void onPresenterCreated() {

    }

    @Override
    public void onPresenterDestroy() {

    }

    @Override
    public String htmlAgreement() {
        //用户协议
        return "http://daymark.zaotaoo.com/#/";
    }

    @Override
    public String htmlPrivacy() {
         //隐私政策
        return "http://daymark.zaotaoo.com/#/";
    }

    @Override
    public String htmlContacts() {
        //联系我们
        return "http://daymark.zaotaoo.com/#/advise";
    }
}
