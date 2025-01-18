package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order

sealed interface FinishOrderEvent {
    data class OnError(val error: String) : FinishOrderEvent
    data object OnOrderSuccess : FinishOrderEvent
}