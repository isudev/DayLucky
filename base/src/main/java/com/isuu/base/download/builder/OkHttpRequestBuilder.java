package com.isuu.base.download.builder;


import com.isuu.base.download.request.RequestCall;

/**
 * Description OkHttpRequestBuilder
 * Created by wangisu@qq.com on 2018/10/31.
 */
public abstract class OkHttpRequestBuilder<T extends OkHttpRequestBuilder> {
    protected String url;
    protected Object tag;


    public T url(String url) {
        this.url = url;
        return (T) this;
    }


    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public abstract RequestCall build();
}
