package com.github.aayman93.wfrleytask.features.orders.data.models.requests

data class SearchProductsRequest(
    val name: String,
    val storeId: Int,
    val merchantId: String
)
