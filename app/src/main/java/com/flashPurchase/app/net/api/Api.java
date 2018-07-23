package com.flashPurchase.app.net.api;

import com.flashPurchase.app.model.CommonResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Description: 接口api
 *
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
    @GET("/login")
    Observable<String> onLogin();


}







