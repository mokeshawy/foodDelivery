package com.saham.fooddelivery.features.common.data.response


import com.google.gson.annotations.SerializedName

data class OrderResponseDto(
    @SerializedName("customerName")
    val customerName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("restaurant")
    val restaurant: String,
    @SerializedName("status")
    val status: String
)