package com.saham.fooddelivery.features.order_details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saham.fooddelivery.R
import com.saham.fooddelivery.features.common_composaple.OrderItem
import com.saham.fooddelivery.features.order_details.presentation.viewmodel.OrderDetailsViewModel
import com.tru.ui_component.failure_view.FailureView
import com.tru.ui_component.main_top_bar.MainTopBar

@Composable
fun OrderDetailsScreen(
    viewModel: OrderDetailsViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiState
    val orderUiModel = uiState.orderUiModel

    MainTopBar(
        isRefreshing = uiState.isLoading,
        onRefresh = viewModel::refresh,
        leftIcon = com.tru.ui_component.R.drawable.ic_vector_arrow_back,
        title = R.string.orderDetails,
        onBackHandler = onBackClicked,
        onLeftIconClicked = onBackClicked
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {

            when {
                uiState.error != null -> {
                    FailureView(onTapToRefresh = viewModel::refresh)
                }

                uiState.orderUiModel != null -> {
                    OrderItem(
                        customerName = orderUiModel.customerName,
                        restaurant = orderUiModel.restaurant,
                        orderStatus = orderUiModel.status,
                    )

                    viewModel.sendMessage(message = "id: ${orderUiModel.id} status: ${orderUiModel.status}")
                }
            }
        }
    }
}