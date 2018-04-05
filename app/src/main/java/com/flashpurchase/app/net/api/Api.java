package com.flashpurchase.app.net.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Description: 接口api
 * Create By: MLS Co,Ltd
 */

public interface Api {

    //下载文件
    @Streaming
    @GET("/api/feeApBx/getFileById")
    Observable<ResponseBody> download(@Query("data_package") String data_package);

    //下载文件
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @Header("Cookie") String Cookie);

    //下载文件
//    @Streaming
//    @GET
//    Observable<ResponseBody> download(@Url String url);


    //登录
//    @POST("/api/login/onLogin")
//    Single<CommonResponse<UserInfo>> onLogin(@Query("data_package") String data_package);


}







