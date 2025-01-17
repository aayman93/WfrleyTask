package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order

import androidx.compose.runtime.Immutable
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product

@Immutable
data class CreateOrderState(
    val isLoading: Boolean = false,
    val searchProducts: List<Product> = emptyList(),
    val query: String = ""
)
