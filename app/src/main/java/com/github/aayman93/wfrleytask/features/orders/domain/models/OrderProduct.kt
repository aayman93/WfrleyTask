package com.github.aayman93.wfrleytask.features.orders.domain.models

import kotlin.random.Random

data class OrderProduct(
    val quantity: Double,
    val product: Product
) {
    fun toOrderDetail(): OrderDetail {
        return OrderDetail(
            id = Random.nextInt(until = 1000),
            quantity = quantity,
            product = product,
            rowTotal = product.price * quantity,
            rowPriceAfterDiscount = product.priceAfterDiscount * quantity
        )
    }
}