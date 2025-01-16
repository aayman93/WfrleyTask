package com.github.aayman93.wfrleytask.features.orders.domain.repository

import com.github.aayman93.wfrleytask.features.orders.domain.models.Order

interface OrdersRepository {

    suspend fun getOrders(
        pageNo: Int,
        pageSize: Int,
        status: Int,
        storeId: Int,
        merchantId: String
    ): List<Order>
}