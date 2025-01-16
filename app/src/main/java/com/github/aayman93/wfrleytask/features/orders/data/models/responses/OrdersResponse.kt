package com.github.aayman93.wfrleytask.features.orders.data.models.responses

import com.github.aayman93.wfrleytask.features.orders.data.models.dto.OrderDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrdersResponse(
    val items: List<OrderDto>?,
    val itemsCount: Int?
)