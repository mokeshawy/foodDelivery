package com.saham.fooddelivery.features.orders_list.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saham.fooddelivery.R
import com.saham.fooddelivery.features.common_composaple.OrderItem
import com.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel
import com.saham.fooddelivery.features.orders_list.presentation.viewmodel.OrderListViewModel
import com.tru.ui_component.failure_view.FailureView
import com.tru.ui_component.main_top_bar.MainTopBar
import com.tru.ui_component.ui_generic.GeneralLazyColumn


@Composable
fun OrdersListScreen(
    viewModel: OrderListViewModel = hiltViewModel(),
    onNavigateToOrderDetails: (orderId: Int) -> Unit
) {

    val uiState = viewModel.uiState

    MainTopBar(
        isRefreshing = uiState.isLoading,
        title = R.string.app_name,
        onRefresh = viewModel::refresh,
        isShowBottomBar = true
    ) {
        when {
            uiState.error != null -> {
                FailureView(onTapToRefresh = viewModel::refresh)
            }

            uiState.orderUiModel?.isNotEmpty() == true -> {
                OrderListContent(
                    orderListUiModelList = uiState.orderUiModel,
                    onItemClicked = { id -> id?.let { onNavigateToOrderDetails(it) } }
                )
            }
        }
    }
}

@Composable
fun OrderListContent(
    orderListUiModelList: List<OrderUiModel?>,
    onItemClicked: (Int?) -> Unit
) {
    GeneralLazyColumn(
        modifier = Modifier.padding(8.dp),
        list = orderListUiModelList
    ) {
        OrderItem(
            customerName = it?.customerName ?: "-",
            restaurant = it?.restaurant ?: "-",
            orderStatus = it?.status ?: "-",
            onClicked = { onItemClicked(it?.id) }
        )
    }
}