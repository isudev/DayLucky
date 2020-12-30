package com.isuu.base.widget.webview;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Description LollipopFixedWebView
 *
 * fix
 * <p>
 *     java.lang.RuntimeException: Unable to start activity ComponentInfo{com.astro90.android/com.astro90.android.view.activity.other.AppWebViewActivity}: android.view.InflateException: Binary XML file line #14: Error inflating class com.tencent.smtt.sdk.WebView
 *     at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2585)
 *     at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:2659)
 *     at android.app.ActivityThread.access$900(ActivityThread.java:188)
 *     at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1521)
 *     at android.os.Handler.dispatchMessage(Handler.java:111)
 *     at android.os.Looper.loop(Looper.java:194)
 *     at android.app.ActivityThread.main(ActivityThread.java:5714)
 *     at java.lang.reflect.Method.invoke(Native Method)
 *     at java.lang.reflect.Method.invoke(Method.java:372)
 *     at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:984)
 *     at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:779)
 * Caused by: android.view.InflateException: Binary XML file line #14: Error inflating class com.tencent.smtt.sdk.WebView
 *
 *
 *      但是在主程序中进行创建时，不会有任何问题。
 *
 *       使用非android5.0系统时，也不会有任何问题。
 *
 *
 *
 *       查看android5.0上WebView空间的代码，发现在android5.0以后，android系统对WebView控件的实现进行了修改：
 *      1, 在WebView控件和浏览器内核之间加入了一层代理层。以屏蔽浏览器内核的差异。目前支持WebKit和Chrome两种内核。
 *      2, 在frameworks/webview目录下新增两个package，一个为webkit，一个为chrome。
 *      3, 代理层通过配置来加载这两个package中间的一个，对浏览器内核进行选择。
 *      4, 具体选用哪个浏览器内核，有com.internal.R.string.config_webViewPackageName这个字符串指定的包名来决定。
 *
 *      在5.0中，com.internal.R.string.config_webViewPackageName的值被获取出来是: com.google.android.webview，也就是chrome内核。在5.1以及以上的版本中，com.internal.R.string.config_webViewPackageName的值被获取出来是: com.android.webview, 也就是webkit内核。
 *
 *       再看看浏览器资源的加载过程：
 *       在WebView控件的初始化函数中，会对package的代码和资源进行加载，其中资源部分被加载到了主程序的AssetManager中。
 *       这里，无论在插件还是在主程序中创建WebView，资源的加载都是在主程序的AssetManager中，这就导致了插件的AssetManager中，并没有浏览器的资源，这就是插件创建WebView找不到资源进而Crash的原因。
 *
 *       为什么5.1没有问题呢？那是因为5.1上使用的是webkit内核，而webkit没有被Google单独出来成为一个apk，所以加载webkit的资源其实是相当于没有加载资源，所以没有问题。
 *
 *       后来还发现一个比较有趣的现象，在MIUI系统上，即使是5.1的版本，也是使用的chrome内核，但是这是插件创建WebView却不会出问题，查了一下，发现是因为小米提前把chrome的资源编译到framework中，在创建assertmanager时就进行了加载，使得插件的assertmanager也拥有chrome内核的资源。
 *
 *
 *
 *        问题搞清楚了之后，这样解决即可：
 *        在插件创建WebView之前，仿照WebView中加载浏览器内核的代码，先去获取一下com.internal.R.string.config_webViewPackageName的值，获得浏览器内核的包名，然后对插件的assertmanager进行浏览器内核资源的加载。经测试问题解决。
 * </p>
 * Created by wangisu@qq.com on 2019-12-11
 */
public class LollipopFixedWebView extends WebView {
    public LollipopFixedWebView(Context context) {
        super(getFixedContext(context));
    }

    public LollipopFixedWebView(Context context, AttributeSet attrs) {
        super(getFixedContext(context), attrs);
    }

    public LollipopFixedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(getFixedContext(context), attrs, defStyleAttr);
    }

    public LollipopFixedWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(getFixedContext(context), attrs, defStyleAttr, defStyleRes);
    }

    private static Context getFixedContext(Context context) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) // Android Lollipop 5.0 & 5.1
            return context.createConfigurationContext(new Configuration());
        return context;
    }

}
