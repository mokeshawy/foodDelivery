package com.saham.fooddelivery.features.order_details.domain.model.state

import com.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel
import com.tru.core.bases.base_viewmodel.ViewState
import com.tru.core.error.AppError

data class OrdersDetailsUiState(
    val isLoading: Boolean = false,
    val error: AppError? = null,
    val orderUiModel: OrderUiModel? = null
) : ViewState
