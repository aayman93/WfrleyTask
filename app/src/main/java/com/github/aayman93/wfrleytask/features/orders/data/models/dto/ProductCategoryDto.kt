package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductCategoryDto(
    val category: CategoryDto?,
    val categoryId: Int?,
    val productId: Int?,
    val rank: Int?
)