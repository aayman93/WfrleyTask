package com.github.aayman93.wfrleytask.features.orders.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateOrderResponse(
    val barcode: String?,
    val id: Int?,
    val priceAfterDiscountTotal: Double?
)