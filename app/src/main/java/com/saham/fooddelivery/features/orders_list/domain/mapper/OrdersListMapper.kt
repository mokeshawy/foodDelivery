package com.saham.fooddelivery.features.orders_list.domain.mapper

import com.saham.fooddelivery.features.orders_list.data.response.OrderResponseDto
import com.saham.fooddelivery.features.orders_list.domain.model.ui.OrderUiModel


fun OrderResponseDto.toOrderUiModel() = OrderUiModel(
    customerName = customerName,
    id = id,
    restaurant = restaurant,
    status = status,
)