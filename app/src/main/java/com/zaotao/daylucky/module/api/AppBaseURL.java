package com.zaotao.daylucky.module.api;

import com.zaotao.daylucky.BuildConfig;

/**
 * Description AppBaseURL
 * Created by wangisu@qq.com on 2019/7/15.
 */

public class AppBaseURL {

    public static final boolean DEBUG = BuildConfig.DEBUG;
//    public static final boolean DEBUG = false;
    /**
     * api base url
     */
    public static final String API_URL = DEBUG ? "http://api.qqxz.top" : "https://app.astro90.com";

    /**
     * web socket base url
     */
    public static final String WS_URL = DEBUG ? "ws://39.106.216.45:7272" : "ws://socket.qqxz.top:7272";

    /**
     * html base url
     */
    public static final String HTML_URL = DEBUG ? "http://astrolabh5.qqxz.xyz/#" : "https://h5.qqxz.top/#";

}
