package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDto(
    val id: Int?,
    val image: String?,
    val isActive: Boolean?,
    val merchantId: String?,
    val minSaleQty: Int?,
    val name: String?,
    @Json(name = "name_En")
    val nameEn: String?,
    @Json(name = "name_Fr")
    val nameFr: String?,
    val parentCategoryId: Int?,
    val rank: Int?
)