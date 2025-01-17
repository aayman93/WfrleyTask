package com.github.aayman93.wfrleytask.features.orders.domain.repository

import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product

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

    suspend fun searchProductsForCustomer(
        name: String,
        storeId: Int,
        merchantId: String
    ): List<Product>
}