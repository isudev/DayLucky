package com.zaotao.daylucky.module.api;

import com.zaotao.daylucky.module.entity.LuckyEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/index/date")
    Observable<BaseResult<LuckyEntity>> initHomeLucky(@Query("astro") int astro);

}
