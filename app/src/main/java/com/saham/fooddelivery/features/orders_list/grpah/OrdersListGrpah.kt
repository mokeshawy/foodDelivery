package com.saham.fooddelivery.features.orders_list.grpah

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.saham.fooddelivery.features.orders_list.presentation.OrdersListScreen
import com.saham.fooddelivery.root_host.RootGraph
import kotlinx.serialization.Serializable


@Serializable
data object OrderListGraph

@Serializable
data object OrderListScreen

fun NavGraphBuilder.orderListGraph(
    navController: NavController,
) {
    navigation<OrderListGraph>(
        startDestination = OrderListScreen::class,
    ) {
        composable<OrderListScreen> {
            OrdersListScreen()
        }
    }
}

fun NavController.navigateToOrderListGraph() {
    navigate(route = OrderListGraph) {
        popUpTo(route = RootGraph) { inclusive = true }
    }
}