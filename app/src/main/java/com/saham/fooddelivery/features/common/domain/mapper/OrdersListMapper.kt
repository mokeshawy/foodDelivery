package com.saham.fooddelivery.features.common.domain.mapper

import com.saham.fooddelivery.features.common.data.response.OrderResponseDto
import com.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel


fun OrderResponseDto.toOrderUiModel() = OrderUiModel(
    customerName = customerName,
    id = id,
    restaurant = restaurant,
    status = status,
)