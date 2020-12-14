package com.zaotao.daylucky;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.zaotao.base.utils.Utils;

public class App extends Application {

    private static Application app;

    public static Application getApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //init base module
        Utils.init(this);
        //umeng
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "3b3fa31a954b4ce5415474cc22dabd03");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

}
