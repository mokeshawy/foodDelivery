package com.saham.fooddelivery.features.order_details.data.repository

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.saham.fooddelivery.features.order_details.domain.repository.OrderDetailsRepository
import com.tru.core.bases.base_repository.BaseRepository
import com.tru.core.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class OrderDetailsRepositoryImpl @Inject constructor(
    private val foodDevilryServices: FoodDevilryServices
) : BaseRepository<Int, Map<String, OrderResponseDto>>(), OrderDetailsRepository {

    override suspend fun getOrderDetailsById(orderId: Int) = flow {
        emit(getOperationState(requestDto = orderId))
    }.flowOn(context = Dispatchers.IO)

    override suspend fun performApiCall(requestDto: Int): State<Map<String, OrderResponseDto>> {
        val response = foodDevilryServices.getOrderDetailsById(equalTo = requestDto)
        return response.handleOrderDetailsResponseState()
    }

    private fun Response<Map<String, OrderResponseDto>>.handleOrderDetailsResponseState() = when {
        isSuccessful -> State.Success(data = body())
        else -> getNotSuccessfulResponseState(response = this)
    }
}