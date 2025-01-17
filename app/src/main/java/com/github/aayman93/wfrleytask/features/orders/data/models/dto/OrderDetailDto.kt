package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderDetailDto(
    val id: Int?,
    val product: ProductDto?,
    val productCustomizationId: Any?,
    val productCustomizations: Any?,
    val quantity: Double?,
    val rowPriceAfterDiscount: Double?,
    val rowTotal: Double?,
    val syncSucceed: Boolean?,
    val totalRefunded: Double?
) {
    fun toOrderDetail(): OrderDetail {
        return OrderDetail(
            id = id ?: -1,
            quantity = quantity ?: 0.0,
            rowPriceAfterDiscount = rowPriceAfterDiscount ?: 0.0,
            rowTotal = rowTotal ?: 0.0,
            product = product?.toProduct() ?: Product()
        )
    }
}