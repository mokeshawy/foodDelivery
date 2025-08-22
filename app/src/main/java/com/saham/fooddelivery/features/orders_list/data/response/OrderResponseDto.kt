package com.saham.fooddelivery.features.orders_list.data.response


import com.google.gson.annotations.SerializedName

data class OrderResponseDto(
    @SerializedName("customerName")
    val customerName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("restaurant")
    val restaurant: String,
    @SerializedName("status")
    val status: String
)