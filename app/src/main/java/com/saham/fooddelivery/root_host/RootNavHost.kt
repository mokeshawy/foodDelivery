package com.saham.fooddelivery.root_host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saham.fooddelivery.features.spalsh_screen.graph.SplashGraph
import com.saham.fooddelivery.features.spalsh_screen.graph.splashGraph
import kotlinx.serialization.Serializable

@Serializable
data object RootGraph


@Composable
internal fun RootNavHost(
    rootController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = rootController,
        startDestination = SplashGraph::class,
        route = RootGraph::class
    ) {
        splashGraph(navController = rootController)
    }
}
