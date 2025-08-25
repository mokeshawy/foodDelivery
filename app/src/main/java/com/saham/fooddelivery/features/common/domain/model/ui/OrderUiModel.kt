package com.saham.fooddelivery.features.common.domain.model.ui

data class OrderUiModel(
    val customerName: String,
    val id: Int,
    val restaurant: String,
    val status: String
)
