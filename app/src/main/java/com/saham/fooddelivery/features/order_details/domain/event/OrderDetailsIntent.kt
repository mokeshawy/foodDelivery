package com.saham.fooddelivery.features.order_details.domain.event

import com.tru.core.bases.base_viewmodel.ViewIntent

sealed class OrderDetailsIntent : ViewIntent {

    data class GetOrderDetailsByIdIntent(val orderId: Int) : OrderDetailsIntent()
}