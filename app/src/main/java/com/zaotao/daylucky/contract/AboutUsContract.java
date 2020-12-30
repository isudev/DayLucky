package com.zaotao.daylucky.contract;

import com.zaotao.daylucky.base.BaseSimplePresenter;
import com.zaotao.daylucky.base.IView;

/**
 * Description AboutUsContract
 * Created by wangisu@qq.com on 12/30/20.
 */
public interface AboutUsContract {

    interface View extends IView {

    }


    interface Presenter extends BaseSimplePresenter<View> {

        String htmlAgreement();

        String htmlPrivacy();

        String htmlContacts();
    }
}
