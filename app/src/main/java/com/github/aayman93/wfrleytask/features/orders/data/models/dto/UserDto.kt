package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val city: Any?,
    val countryCode: Any?,
    @Json(name = "default_customer")
    val defaultCustomer: Any?,
    val displayName: String?,
    val id: String?,
    val isCompletProfile: Boolean?,
    val isPaid: Any?,
    val merchantId: String?,
    val merchantRefNumber: Any?,
    val merchantType: Any?,
    val organizationType: Any?,
    val password: Any?,
    val paymentMethod: Any?,
    val stockID: Any?,
    @Json(name = "store_code")
    val storeCode: Any?,
    val storeId: Any?,
    val storeType: Any?,
    val street: Any?,
    val subDomain: Any?,
    val subscripeDate: Any?,
    val subtype: Any?,
    val token: Any?,
    val tokenExpireDate: Any?,
    val userName: String?,
    val userRoles: List<Any>?,
    val verifiedType: Int?
)