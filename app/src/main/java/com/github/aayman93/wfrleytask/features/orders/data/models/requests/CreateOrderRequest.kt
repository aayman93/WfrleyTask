package com.github.aayman93.wfrleytask.features.orders.data.models.requests

data class CreateOrderRequest(
    val customerId: String,
    val customerServiceUserId: String,
    val storeId: Int,
    val paymentDeliveryMethod: Int,
    val postponedDate: String,
    val addressId: Int,
    val priceAfterDiscountTotal: Double,
    val orderDeliveryMethod: Int,
    val orderDetails: List<OrderDetailRequest>
)
