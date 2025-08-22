package com.saham.fooddelivery.features.spalsh_screen.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.saham.fooddelivery.features.spalsh_screen.presentation.screen.SplashScreen
import kotlinx.serialization.Serializable


@Serializable
data object SplashGraph

@Serializable
data object SplashScreen

fun NavGraphBuilder.splashGraph(
    navController: NavController,
) {
    navigation<SplashGraph>(
        startDestination = SplashScreen::class,
    ) {
        composable<SplashScreen> {
            SplashScreen(onNavigateToOrderList = {
                //TODO NAVIGATE TO ORDER-LIST
            })
        }
    }
}
