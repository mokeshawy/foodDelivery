package com.saham.fooddelivery.features.orders_list.presentation.viewmodel

import com.saham.fooddelivery.features.orders_list.domain.event.OrderListIntent
import com.saham.fooddelivery.features.orders_list.domain.model.state.OrdersListUiState
import com.saham.fooddelivery.features.orders_list.domain.usecase.OrdersListUseCase
import com.tru.core.bases.base_viewmodel.BaseViewModel
import com.tru.core.extensions.collectOnFlowState

import com.tru.core.extensions.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val ordersListUseCase: OrdersListUseCase
) : BaseViewModel<OrderListIntent, OrdersListUiState>(OrdersListUiState()) {


    init {
        sendGetOrdersListIntent()
    }


    fun refresh() {
        resetOrdersListUiState()
        sendGetOrdersListIntent()
    }

    private fun sendGetOrdersListIntent() = sendIntent(OrderListIntent.GetOrdersListIntent)


    override fun processIntent(intent: OrderListIntent) {
        when (intent) {
            OrderListIntent.GetOrdersListIntent -> reduceOrdersListResponseState()
        }
    }


    private fun reduceOrdersListResponseState() = viewModelScope {
        updateStateOf { copy(isLoading = true) }
        ordersListUseCase().collectOnFlowState(
            onError = {
                handleError(it) { updateStateOf { copy(isLoading = false, error = it) } }
            },
            onSuccess = {
                val ordersListUiModel = ordersListUseCase.ordersListUiModel
                updateStateOf { copy(isLoading = false, orderUiModel = ordersListUiModel) }
            })
    }


    private fun resetOrdersListUiState() =
        updateStateOf { copy(isLoading = false, error = null, orderUiModel = null) }
}