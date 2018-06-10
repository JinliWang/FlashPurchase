package com.flashPurchase.app.net.manager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.app.library.net.okhttp.https.HttpsUtils;
import com.flashPurchase.app.BaseApplication;
import com.flashPurchase.app.Constant.Host;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.net.api.Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: 网络管理
 * Create By: MLS Co,Ltd
 */

public class ApiManager {

    public static Retrofit retrofit;
    public static Api api;


    static {
        init();
    }

    public static void init() {
        retrofit = getRetrofit(provideOkHttpClient(), Host.http + SpManager.getHost() + "/api/");
        api = getRetrofit(provideOkHttpClient(), Host.http + Host.PUBLIC_HOST + "/api/").create(Api.class);
    }

    private static Retrofit getRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(180, TimeUnit.SECONDS)//超时时间
                .readTimeout(180, TimeUnit.SECONDS)//超时时间
                .writeTimeout(180, TimeUnit.SECONDS)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(HttpsUtils.initSSLSocketFactory())
                .addInterceptor(new Interceptor() {//添加拦截器
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        try {

                            Request request = chain.request();
                            Headers.Builder builder = request.headers().newBuilder();

                            builder.add("h_token", SpManager.getUserName());
                            builder.add("h_entid", SpManager.getEntId());
                            Headers headers = builder.build();

                            request = request.newBuilder().headers(headers).build();

                            Response response = chain.proceed(request);
                            //这里获取请求返回的cookie
                            if (!response.headers("Set-Cookie").isEmpty()) {
                                List<String> setCookies = response.headers("Set-Cookie");
                                List<String> cookies = new ArrayList<String>();
                                for (String cookie : setCookies) {
                                    String[] a = cookie.split(";");
                                    if (a == null)
                                        continue;
                                    for (String s : a) {
                                        if (s.startsWith("c_token") || s.startsWith("JSESSIONID")) {
                                            cookies.add(s);
                                        }
                                    }
                                }
                                SpManager.setCookie(cookies);
                            }

//                        Log.d("Response Code", response.code() + "");
//                            if (response.code() == 302) {//这个地方可以根据返回码做一些事情。通过sendBroadcast发出去。
//                                ToastUtil.show("登陆已失效，请重新登录");
//                                SpManager.loginOut();
//                                Intent intent = new Intent(BaseConstant.getInstance(), LoginActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                BaseConstant.getInstance().startActivity(intent);
//                                ActivityManager.getActivityManager().popAllActivity();
//
//                            }
                            return response;

                        } catch (Exception e) {
                            return chain.proceed(chain.request());
                        }
                    }
                })
//                .addInterceptor(loggingInterceptor)//把上面的log拦截器添加进来
//                .cache(cache)//添加缓存
        ;//build生效
        if (BaseApplication.isTest) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();//返回client
    }

    public static Api getApi() {
        return api;
    }
}
