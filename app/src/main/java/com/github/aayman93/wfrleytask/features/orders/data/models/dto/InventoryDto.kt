package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InventoryDto(
    val id: Int?,
    val name: String?,
    val store: StoreDto?,
    val storeId: Int?
)