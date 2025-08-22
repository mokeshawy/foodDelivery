package com.saham.fooddelivery.features.orders_list.domain.repository

import com.saham.fooddelivery.features.orders_list.data.response.OrderResponseDto
import com.tru.core.state.State
import kotlinx.coroutines.flow.Flow

interface OrdersListRepository {

    suspend fun getOrdersList(): Flow<State<List<OrderResponseDto>>>
}