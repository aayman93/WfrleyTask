package com.github.aayman93.wfrleytask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.github.aayman93.wfrleytask.features.orders.presentation.home.HomeScreen
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.NewOrderViewModel
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order.CreateOrderScreen
import com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order.FinishOrderScreen
import com.github.aayman93.wfrleytask.features.orders.presentation.order_details.OrderDetailsScreen

@Composable
fun WfrleyApp(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                onCreateOrderClick = {
                    navController.navigate(NewOrderGraphRoute)
                },
                onOrderClick = { orderId ->
                    navController.navigate(OrderDetailsRoute(orderId))
                }
            )
        }
        composable<OrderDetailsRoute> {
            OrderDetailsScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onHomeClick = {
                    navController.navigateUp()
                }
            )
        }
        navigation<NewOrderGraphRoute>(startDestination = CreateOrderRoute) {
            composable<CreateOrderRoute> { navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(NewOrderGraphRoute)
                }
                val viewModel: NewOrderViewModel = hiltViewModel(parentEntry)

                CreateOrderScreen(
                    sharedViewModel = viewModel,
                    onConfirmOrder = {
                        navController.navigate(FinishOrderRoute)
                    },
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onHomeClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable<FinishOrderRoute> { navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(NewOrderGraphRoute)
                }
                val viewModel: NewOrderViewModel = hiltViewModel(parentEntry)

                FinishOrderScreen(
                    sharedViewModel = viewModel,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}