package com.saham.test_api_services

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import retrofit2.Response
import javax.inject.Inject

class TestApiService @Inject constructor() : FoodDevilryServices {

    override suspend fun getOrdersList(): Response<List<OrderResponseDto>> {
        return Response.success(
            listOf(
                OrderResponseDto(
                    restaurant = "Haute Dog Diner",
                    id = 2,
                    customerName = "Henry",
                    status = "Out for delivery"
                ),
                OrderResponseDto(
                    restaurant = "Puns n’ Noodles",
                    id = 3,
                    customerName = "Jack",
                    status = "Delivered"
                )
            )
        )
    }

    override suspend fun getOrderDetailsById(equalTo: Int): Response<Map<String, OrderResponseDto>> {
        TODO("Not yet implemented")
    }
}