package com.github.aayman93.wfrleytask.features.orders.data.models.requests

data class OrdersPagingRequest(
    val pageSize: Int,
    val pageNo: Int,
    val status: Int,
    val storeId: Int,
    val merchantId: String
)
