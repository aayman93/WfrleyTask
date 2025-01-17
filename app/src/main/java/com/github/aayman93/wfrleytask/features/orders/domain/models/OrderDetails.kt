package com.github.aayman93.wfrleytask.features.orders.domain.models

data class OrderDetails(
    val id: Int,
    val orderNumber: String,
    val orderDate: String,
    val customerName: String,
    val discountAmount: Double,
    val grandTotal: Double,
    val items: List<OrderDetail>,
    val shippingAddress: ShippingAddress
)
