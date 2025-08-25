package com.saham.fooddelivery.features.orders_list.domain.model.state

import com.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel
import com.tru.core.bases.base_viewmodel.ViewState
import com.tru.core.error.AppError

data class OrdersListUiState(
    val isLoading : Boolean = false,
    val error: AppError? = null,
    val orderUiModel: List<OrderUiModel>? = null
) : ViewState
