package com.saham.fooddelivery.features.orders_list.domain.usecase

import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.saham.fooddelivery.features.common.domain.mapper.toOrderUiModel
import com.saham.fooddelivery.features.orders_list.domain.repository.OrdersListRepository
import com.tru.core.state.State
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class OrdersListUseCase @Inject constructor(private val ordersListRepository: OrdersListRepository) {


    private var ordersList: List<OrderResponseDto>? = null

    operator fun invoke() = channelFlow {
        val response = async { ordersListRepository.getOrdersList() }
        response.await().collect {
            if (it is State.Success) {
                ordersList = it.data
            }
            send(it)
        }
    }

    val ordersListUiModel get() = ordersList?.map { it.toOrderUiModel() } ?: emptyList()
}