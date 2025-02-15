package com.github.aayman93.wfrleytask.features.orders.domain.models

data class Product(
    val id: Int = -1,
    val name: String = "",
    val price: Double = 0.0,
    val priceAfterDiscount: Double = 0.0,
    val saleableQuantity: Double = 0.0
)
