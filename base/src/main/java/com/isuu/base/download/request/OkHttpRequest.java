package com.isuu.base.download.request;


import com.isuu.base.download.callback.Callback;
import com.isuu.base.download.other.Exceptions;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description OkHttpRequest
 * Created by wangisu@qq.com on 2018/10/31.
 */
public abstract class OkHttpRequest {
    protected String url;
    protected Object tag;

    protected Request.Builder builder = new Request.Builder();

    OkHttpRequest(String url, Object tag) {
        this.url = url;
        this.tag = tag;

        if (url == null) {
            Exceptions.illegalArgument("url can not be null.");
        }

        initBuilder();
    }


    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder() {
        builder.url(url).tag(tag);
    }

    protected abstract RequestBody buildRequestBody();

    private RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    protected abstract Request buildRequest(RequestBody requestBody);

    public RequestCall build() {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback) {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }


}
