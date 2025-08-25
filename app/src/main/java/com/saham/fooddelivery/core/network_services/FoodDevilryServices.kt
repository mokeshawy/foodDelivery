package com.saham.fooddelivery.core.network_services

import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDevilryServices {


    @GET("orders.json")
    suspend fun getOrdersList(): Response<List<OrderResponseDto>>

    @GET("orders.json?orderBy=\"id\"")
    suspend fun getOrderDetailsById(@Query("equalTo") equalTo: Int): Response<Map<String, OrderResponseDto>>

}