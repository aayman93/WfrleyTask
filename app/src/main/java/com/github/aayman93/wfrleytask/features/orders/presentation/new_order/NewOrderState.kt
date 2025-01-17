package com.github.aayman93.wfrleytask.features.orders.presentation.new_order

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderProduct

data class NewOrderState(
    val selectedProducts: List<OrderProduct> = emptyList()
) {
    val isOrderEmpty: Boolean
        get() = selectedProducts.isEmpty()

    val totalPrice: Double
        get() = selectedProducts.sumOf { it.product.price * it.quantity }

    val totalQuantity: Double
        get() = selectedProducts.sumOf { it.quantity }
}
