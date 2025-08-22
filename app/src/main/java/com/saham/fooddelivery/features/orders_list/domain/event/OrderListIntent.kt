package com.saham.fooddelivery.features.orders_list.domain.event

import com.tru.core.bases.base_viewmodel.ViewIntent

sealed class OrderListIntent : ViewIntent {

    data object GetOrdersListIntent : OrderListIntent()
}