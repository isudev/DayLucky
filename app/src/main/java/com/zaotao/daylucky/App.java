package com.zaotao.daylucky;

import android.app.Application;

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
        UMConfigure.init(this, null, null, UMConfigure.DEVICE_TYPE_PHONE, null);
    }

}
