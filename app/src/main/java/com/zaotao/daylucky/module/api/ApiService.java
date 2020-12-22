package com.zaotao.daylucky.module.api;

import com.zaotao.daylucky.module.entity.LuckyEntity;
import com.zaotao.daylucky.module.entity.LuckyVipEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/index/date")
    Observable<BaseResult<LuckyEntity>> initHomeLucky(@Query("astro") int astro);

    @GET("/index/daylucky")
    Observable<BaseResult<LuckyVipEntity>> initVipLucky(@Query("astro") int astro, @Query("mobile") String mobile, @Query("sign") String sign);
}
