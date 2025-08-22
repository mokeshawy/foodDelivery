package com.saham.fooddelivery.features.order_details.domain.repository

import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.tru.core.state.State
import kotlinx.coroutines.flow.Flow

interface OrderDetailsRepository {


    suspend fun getOrderDetailsById(orderId: Int): Flow<State<Map<String, OrderResponseDto>>>
}