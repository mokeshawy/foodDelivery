package com.saham.fooddelivery.order_list.data.repository

import com.saham.fooddelivery.core.network_services.FoodDevilryServices
import com.saham.fooddelivery.features.orders_list.domain.repository.OrdersListRepository
import com.tru.core.state.State
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestOrderListRepositoryImpl @Inject constructor(private val foodDevilryServices: FoodDevilryServices) :
    OrdersListRepository {


    override suspend fun getOrdersList() = flow {
        emit(State.Loading())
        when {
            foodDevilryServices.getOrdersList().isSuccessful -> emit(
                value = State.Success(data = foodDevilryServices.getOrdersList().body())
            )
        }
    }
}