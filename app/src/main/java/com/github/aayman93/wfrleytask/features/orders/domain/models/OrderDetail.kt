package com.github.aayman93.wfrleytask.features.orders.domain.models

data class OrderDetail(
    val id: Int,
    val quantity: Double,
    val rowPriceAfterDiscount: Double,
    val rowTotal: Double,
    val product: Product
)
