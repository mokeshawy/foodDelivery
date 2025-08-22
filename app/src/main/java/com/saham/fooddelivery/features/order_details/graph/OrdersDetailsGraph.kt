package com.saham.fooddelivery.features.order_details.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.saham.fooddelivery.features.order_details.presentation.OrderDetailsScreen
import kotlinx.serialization.Serializable


@Serializable
data class OrderDetailsScreen(val orderId: Int)

fun NavGraphBuilder.orderDetailsGraph(
    navController: NavController,
) {
    composable<OrderDetailsScreen> {
        OrderDetailsScreen(onBackClicked = navController::popBackStack)
    }
}

fun NavController.navigateToOrderDetailsGraph(
    orderId: Int
) {
    navigate(route = OrderDetailsScreen(orderId = orderId)) {
        restoreState = true
    }
}