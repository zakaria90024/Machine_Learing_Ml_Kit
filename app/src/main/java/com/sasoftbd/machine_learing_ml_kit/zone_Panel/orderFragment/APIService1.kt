package com.sasoftbd.machine_learing_ml_kit.zone_Panel.orderFragment

import retrofit2.Call
import retrofit2.http.GET

interface APIService1 {
//    @GET("GetCustomer/157")
//    suspend fun getEmployee(): Response<OrderModel>

    @GET("GetCustomer/157")
    fun getEmployee(): Call<ArrayList<OrderModel>?>?
}