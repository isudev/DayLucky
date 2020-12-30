package com.isuu.base.download.callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Description Callback
 * Created by wangisu@qq.com on 2018/10/31.
 */
public abstract class Callback<T> {

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total) {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateResponse(Response response) {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;

    public abstract void onError(Call call, Exception e);

    public abstract void onResponse(T response);


    public static Callback CALLBACK_DEFAULT = new Callback() {

        @Override
        public Object parseNetworkResponse(Response response) throws Exception {
            return null;
        }

        @Override
        public void onError(Call call, Exception e) {

        }

        @Override
        public void onResponse(Object response) {

        }
    };

}