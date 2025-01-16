package com.github.aayman93.wfrleytask.features.orders.domain.models

data class Order(
    val id: Int,
    val customerName: String,
    val orderDate: String,
    val price: Double
)
