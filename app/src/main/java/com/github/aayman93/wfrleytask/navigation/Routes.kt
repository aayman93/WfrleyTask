package com.github.aayman93.wfrleytask.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class OrderDetailsRoute(
    val id: Int
)

@Serializable
object NewOrderGraphRoute

@Serializable
object CreateOrderRoute

@Serializable
object FinishOrderRoute
