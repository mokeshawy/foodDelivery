package com.saham.fooddelivery.features.orders_list.data.repository

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.saham.fooddelivery.features.orders_list.domain.repository.OrdersListRepository
import com.tru.core.bases.base_repository.BaseRepository
import com.tru.core.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class OrdersListRepositoryImpl @Inject constructor(private val foodDevilryServices: FoodDevilryServices) :
    BaseRepository<Any, List<OrderResponseDto>>(),
    OrdersListRepository {

    override suspend fun getOrdersList() = flow {
        emit(getOperationState(requestDto = Any()))
    }.flowOn(Dispatchers.IO)

    override suspend fun performApiCall(requestDto: Any): State<List<OrderResponseDto>> {
        val response = foodDevilryServices.getOrdersList()
        return response.handleOrdersListResponseState()
    }


    private fun Response<List<OrderResponseDto>>.handleOrdersListResponseState() = when {
        isSuccessful -> State.Success(data = this.body())
        else -> getNotSuccessfulResponseState(response = this)
    }
}