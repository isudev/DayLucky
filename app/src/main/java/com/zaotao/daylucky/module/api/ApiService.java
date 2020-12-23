package com.zaotao.daylucky.module.api;

import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;
import com.zaotao.daylucky.module.entity.OrderPayEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/index/date")
    Observable<BaseResult<LuckyEntity>> apiMainLucky(@Query("astro") int astro);

    @GET("/index/daylucky")
    Observable<BaseResult<LuckyVipEntity>> apiVipLucky(@Query("astro") int astro, @Query("mobile") String mobile, @Query("sign") String sign);

    @POST("/daylucky/order")
    Observable<BaseResult<OrderPayEntity>> apiOrderPay(@Body OrderPayEntity orderPayEntity);
}
