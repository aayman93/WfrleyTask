package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.github.aayman93.wfrleytask.features.orders.domain.models.ShippingAddress
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShippingAddressDto(
    val cityName: String?,
    val countryKey: String?,
    val id: Int?,
    val postCode: Any?,
    val street: String?,
    val telephone: String?
) {
    fun toShippingAddress(): ShippingAddress {
        return ShippingAddress(
            cityName = cityName.orEmpty(),
            countryKey = countryKey.orEmpty(),
            id = id ?: -1,
            postCode = postCode,
            street = street.orEmpty(),
            telephone = telephone.orEmpty()
        )
    }
}