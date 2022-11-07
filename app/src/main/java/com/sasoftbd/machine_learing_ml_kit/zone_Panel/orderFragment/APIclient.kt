package com.sasoftbd.machine_learing_ml_kit.zone_Panel.orderFragment

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIclient {
    //
    val BASE_URL = "http://192.168.1.83:8080/api/" //local


    //public static final String BASE_URL = "http://182.160.126.21:8082/api/";  //real server3


    //public static final String BASE_URL = "http://182.160.126.21:8082/api/";  //real server3
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    private fun okClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}