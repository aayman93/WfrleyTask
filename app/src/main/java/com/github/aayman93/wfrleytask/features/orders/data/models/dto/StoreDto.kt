package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreDto(
    val code: String?,
    val id: Int?,
    val inventories: List<StoreInventoryDto>?,
    val isActive: Boolean?,
    val name: String?
)