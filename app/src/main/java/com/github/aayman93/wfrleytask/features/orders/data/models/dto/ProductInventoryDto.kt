package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductInventoryDto(
    val id: Int?,
    val inventoryId: Int?,
    val price: Double?,
    val priceAfterDiscount: Double?,
    val productId: Int?,
    val quantity: Double?,
    val salableQuantity: Double?,
    val status: Boolean?
)