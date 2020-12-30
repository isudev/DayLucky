package com.isuu.base.download.builder;


import com.isuu.base.download.request.GetRequest;
import com.isuu.base.download.request.RequestCall;

/**
 * Description GetBuilder
 * Created by wangisu@qq.com on 2018/10/31.
 */
public class GetBuilder extends OkHttpRequestBuilder<GetBuilder> {
    @Override
    public RequestCall build() {
        return new GetRequest(url, tag).build();
    }
}
