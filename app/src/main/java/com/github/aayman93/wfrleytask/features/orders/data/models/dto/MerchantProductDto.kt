package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantProductDto(
    val additionalBarcode: Any?,
    val alwaysAvailable: Boolean?,
    val childQuantity: Any?,
    val dateFrom: String?,
    val dateTo: String?,
    val description: String?,
    val initialValue: Double?,
    val isMostUsed: Boolean?,
    val isOffer: Boolean?,
    val maxQuantity: Double?,
    val price: Double?,
    val priceAfterDiscount: Double?,
    val productApparence: Int?,
    val productApprovalStatus: Int?,
    val status: Int?
)