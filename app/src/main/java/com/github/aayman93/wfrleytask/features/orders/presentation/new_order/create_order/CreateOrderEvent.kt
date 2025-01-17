package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order

sealed interface CreateOrderEvent {
    data class OnError(val error: String) : CreateOrderEvent
}