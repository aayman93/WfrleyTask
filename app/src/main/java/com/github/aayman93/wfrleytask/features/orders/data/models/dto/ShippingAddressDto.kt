package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShippingAddressDto(
    val cityName: String?,
    val countryKey: String?,
    val id: Int?,
    val postCode: Any?,
    val street: String?,
    val telephone: String?
)