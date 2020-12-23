package com.zaotao.daylucky.module.api;

import com.zaotao.daylucky.BuildConfig;

/**
 * Description AppBaseURL
 * Created by wangisu@qq.com on 2019/7/15.
 */

public class AppBaseURL {

//    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final boolean DEBUG = false;
    /**
     * api base url
     */
    public static final String API_URL = DEBUG ? "http://api.qqxz.top" : "https://app.astro90.com";

}
