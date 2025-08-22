package com.saham.fooddelivery.features.orders_list.domain.model.ui

data class OrderUiModel(
    val customerName: String,
    val id: String,
    val restaurant: String,
    val status: String
)
