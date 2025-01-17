package com.github.aayman93.wfrleytask.features.orders.presentation.order_details

import androidx.compose.runtime.Immutable
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails

@Immutable
data class OrderDetailsState(
    val isLoading: Boolean = false,
    val orderDetails: OrderDetails? = null
)
