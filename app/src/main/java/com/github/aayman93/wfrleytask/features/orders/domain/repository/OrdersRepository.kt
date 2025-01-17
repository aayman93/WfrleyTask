package com.github.aayman93.wfrleytask.features.orders.domain.repository

import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails

interface OrdersRepository {

    suspend fun getOrders(
        pageNo: Int,
        pageSize: Int,
        status: Int,
        storeId: Int,
        merchantId: String
    ): List<Order>

    suspend fun getOrderDetails(
        orderId: Int,
        merchantId: String
    ): OrderDetails
}