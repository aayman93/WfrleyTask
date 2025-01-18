package com.github.aayman93.wfrleytask.features.orders.data.models.requests

data class OrderDetailRequest(
    val quantity: Double,
    val rowTotal: Double,
    val rowPriceAfterDiscount: Double,
    val porductId: Int
)
