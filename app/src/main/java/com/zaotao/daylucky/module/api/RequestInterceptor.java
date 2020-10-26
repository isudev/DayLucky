package com.zaotao.daylucky.module.api;


import com.zaotao.base.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description RequestInterceptor
 * Created by wangisu@qq.com on 2019-07-15
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtils.isConnected()) {
            Request.Builder compressedRequest = request.newBuilder();
            compressedRequest.addHeader("version", "v140");

           /* Request compressedRequest = request.newBuilder()
                    .header("version", Constants.HEADER_VERSION)
                    .header("token", UserInfoManager.getInstance().getAccessToken())
                    .build();*/
            return chain.proceed(compressedRequest.build());
        } else {
            Response originalResponse = chain.proceed(request);
            return originalResponse.newBuilder()
                    .header("Connection", "close")
                    .removeHeader("Pragma").build();
        }

    }
}
