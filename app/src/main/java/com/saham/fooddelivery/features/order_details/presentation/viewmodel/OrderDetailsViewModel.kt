package com.saham.fooddelivery.features.order_details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.saham.fooddelivery.features.order_details.domain.event.OrderDetailsIntent
import com.saham.fooddelivery.features.order_details.domain.model.state.OrdersDetailsUiState
import com.saham.fooddelivery.features.order_details.domain.usecase.OrderDetailsUseCase
import com.saham.fooddelivery.features.order_details.graph.OrderDetailsScreen
import com.tru.core.bases.base_viewmodel.BaseViewModel
import com.tru.core.extensions.collectOnFlowState
import com.tru.core.extensions.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val orderDetailsUseCase: OrderDetailsUseCase
) :
    BaseViewModel<OrderDetailsIntent, OrdersDetailsUiState>(OrdersDetailsUiState()) {

    val orderId = savedStateHandle.toRoute<OrderDetailsScreen>().orderId

    init {
        sendGetOrderDetailsByIdIntent()
    }

    fun refresh() {
        resetOrderDetailsUiState()
        sendGetOrderDetailsByIdIntent()
    }

    private fun sendGetOrderDetailsByIdIntent() =
        sendIntent(OrderDetailsIntent.GetOrderDetailsByIdIntent(orderId = orderId))

    override fun processIntent(intent: OrderDetailsIntent) {
        when (intent) {
            is OrderDetailsIntent.GetOrderDetailsByIdIntent -> {
                reduceOrderDetailsResponseState(orderId = intent.orderId)
            }
        }
    }

    private fun reduceOrderDetailsResponseState(orderId: Int) = viewModelScope {
        updateStateOf { copy(isLoading = true) }
        orderDetailsUseCase(orderId = orderId).collectOnFlowState(
            onError = {
                handleError(it) { updateStateOf { copy(isLoading = false, error = it) } }
            }, onSuccess = {
                val orderUiModel = orderDetailsUseCase.orderUiModel
                updateStateOf { copy(isLoading = false, orderUiModel = orderUiModel) }
            }
        )
    }

    private fun resetOrderDetailsUiState() =
        updateStateOf { copy(isLoading = false, error = null, orderUiModel = null) }
}