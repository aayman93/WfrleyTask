package com.github.aayman93.wfrleytask.features.orders.domain.models

data class ShippingAddress(
    val cityName: String = "",
    val countryKey: String = "",
    val id: Int = -1,
    val postCode: Any? = null,
    val street: String = "",
    val telephone: String = ""
)
