package com.github.aayman93.wfrleytask.features.orders.presentation.order_details

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails

data class OrderDetailsState(
    val isLoading: Boolean = false,
    val orderDetails: OrderDetails? = null
)
