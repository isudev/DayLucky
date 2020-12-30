package com.isuu.base.download.request;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description GetRequest
 * Created by wangisu@qq.com on 2018/10/31.
 */
public class GetRequest extends OkHttpRequest {
    public GetRequest(String url, Object tag) {
        super(url, tag);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody) {
        return builder.get().build();
    }


}
