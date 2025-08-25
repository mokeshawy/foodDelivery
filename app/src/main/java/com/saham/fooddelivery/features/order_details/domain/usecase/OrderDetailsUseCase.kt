package com.saham.fooddelivery.features.order_details.domain.usecase

import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.saham.fooddelivery.features.common.domain.mapper.toOrderUiModel
import com.saham.fooddelivery.features.order_details.domain.repository.OrderDetailsRepository
import com.tru.core.state.State
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class OrderDetailsUseCase @Inject constructor(private val orderDetailsRepository: OrderDetailsRepository) {


    private var orderResponseDto: OrderResponseDto? = null


    operator fun invoke(orderId: Int) = channelFlow {
        val response = async { orderDetailsRepository.getOrderDetailsById(orderId = orderId) }
        response.await().collect {
            if (it is State.Success) {
                it.data?.values?.map { value -> orderResponseDto = value }
            }
            send(it)
        }
    }


    val orderUiModel get() = orderResponseDto?.toOrderUiModel()
}