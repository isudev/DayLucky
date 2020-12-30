package com.zaotao.daylucky.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.widget.ContentLoadingProgressBar;

import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.isuu.base.listener.OnAppBarStartViewListener;
import com.isuu.base.widget.title.AppTitleBarLayout;
import com.isuu.base.widget.webview.LollipopFixedWebView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseSimpleActivity;
import com.zaotao.daylucky.presenter.WebViewPresenter;

import butterknife.BindView;

public class AppWebViewActivity extends BaseSimpleActivity {

    @BindView(R.id.app_bar_app_web_view)
    AppTitleBarLayout appBarAppWebView;
    @BindView(R.id.app_web_view_progress)
    ContentLoadingProgressBar appWebViewProgress;
    @BindView(R.id.app_web_view)
    LollipopFixedWebView webView;

    private WebViewPresenter webViewPresenter;

    private boolean isUseGoBack;

    public static void startAppWebViewActivity(Context context, String url, String title) {
        Intent intent = new Intent();
        intent.putExtra("web_view_url", url);
        intent.putExtra("web_view_title", title);
        intent.setClass(context, AppWebViewActivity.class);
        context.startActivity(intent);
    }

    public static void startAppWebViewActivity(Context context, String url) {
        Intent intent = new Intent();
        intent.putExtra("web_view_url", url);
        intent.setClass(context, AppWebViewActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_web_view;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @Override
    protected void initCreate(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra("web_view_url");
        String title = getIntent().getStringExtra("web_view_title");
        /**
         * filter url is use go back
         */
        parseUseGoBack(url);
        appBarAppWebView.setStartImageResource(R.drawable.ic_back);
        appBarAppWebView.setTitleText(TextUtils.isEmpty(title) ? "" : title);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");
        webViewPresenter = new WebViewPresenter(mActivity, mContext);
        webView.addJavascriptInterface(webViewPresenter, "Android");
        webView.setDrawingCacheEnabled(true);
        webView.loadUrl(url);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webSettings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webSettings.setSupportZoom(false);//是否可以缩放，默认true
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage
        webSettings.setBlockNetworkImage(false);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

    }

    @Override
    protected void initListener() {
        appBarAppWebView.setOnAppBarStartViewListener(new OnAppBarStartViewListener() {
            @Override
            public void onClick(View view) {
                if (isUseGoBack) {
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                } else {
                    finish();
                }
            }
        });
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!(url.startsWith("http") || url.startsWith("https"))) {
                    return true;
                }

                /**
                 * 推荐采用的新的二合一接口(payInterceptorWithUrl),只需调用一次
                 * aliPay h5
                 */
                final PayTask task = new PayTask(mActivity);
                boolean isIntercepted = task.payInterceptorWithUrl(url, true, new H5PayCallback() {
                    @Override
                    public void onPayResult(final H5PayResultModel result) {
                        // 支付结果返回
                        final String url = result.getReturnUrl();
                        if (!TextUtils.isEmpty(url)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.loadUrl(url);
                                }
                            });
                        }
                    }
                });

                /**
                 * 判断是否成功拦截
                 * 若成功拦截，则无需继续加载该URL；否则继续加载
                 */
                if (!isIntercepted) {
                    view.loadUrl(url);
                }
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                appWebViewProgress.setProgress(newProgress);
                if (newProgress == 100) {
                    appWebViewProgress.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    protected void onDestroy() {
        try {
            if (webView != null) {
                webView.stopLoading();
                webView.removeAllViewsInLayout();
                webView.removeAllViews();
                webView.setWebViewClient(null);
                CookieManager.getInstance().flush();
                webView.destroy();
                webView = null;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            super.onDestroy();
        }
        webViewPresenter.onDestroy();
    }

    private void parseUseGoBack(String url) {
        try {
            String[] urlParts = url.split("\\?");
            String useGoBack = "use_go_back";
            if (urlParts.length > 1) {
                String[] params = urlParts[1].split("&");

                for (String param :
                        params) {
                    String[] keyValue = param.split("=");
                    if (keyValue[0].contains(useGoBack)) {
                        isUseGoBack = ((keyValue[1] != null) && keyValue[1].equalsIgnoreCase("true"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isUseGoBack = false;
        }
    }
}
