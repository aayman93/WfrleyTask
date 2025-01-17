package com.github.aayman93.wfrleytask.features.orders.presentation.order_details

sealed interface OrderDetailsEvent {
    data class OnError(val error: String) : OrderDetailsEvent
}