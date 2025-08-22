package com.saham.fooddelivery.core.network_services

import com.saham.fooddelivery.features.orders_list.data.response.OrderResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface FoodDevilryServices {


    @GET("orders.json")
    suspend fun getOrdersList(): Response<List<OrderResponseDto>>

}