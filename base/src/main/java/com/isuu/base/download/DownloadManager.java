package com.isuu.base.download;



import com.isuu.base.download.builder.GetBuilder;
import com.isuu.base.download.callback.Callback;
import com.isuu.base.download.other.Platform;
import com.isuu.base.download.request.RequestCall;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Description DownloadManager
 * Created by wangisu@qq.com on 2018/10/31.
 */
public class DownloadManager {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static DownloadManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    private DownloadManager(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    private static DownloadManager initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (DownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new DownloadManager(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static DownloadManager getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }


    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback);
                        return;
                    }

                    if (!finalCallback.validateResponse(response)) {
                        sendFailResultCallback(call, new IOException("request failed , Response's code is : " + response.code()), finalCallback);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response);
                    sendSuccessResultCallback(o, finalCallback);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    private void sendFailResultCallback(final Call call, final Exception e, final Callback callback) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e);
            }
        });
    }

    private void sendSuccessResultCallback(final Object object, final Callback callback) {
        if (callback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
            }
        });
    }

}

