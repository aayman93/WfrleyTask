package com.github.aayman93.wfrleytask.features.orders.data.repository

import com.github.aayman93.wfrleytask.features.orders.data.data_source.OrdersService
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.OrdersPagingRequest
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersRepositoryImpl @Inject constructor(
    private val service: OrdersService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : OrdersRepository {

    override suspend fun getOrders(
        pageNo: Int,
        pageSize: Int,
        status: Int,
        storeId: Int,
        merchantId: String
    ): List<Order> {
        return withContext(dispatcher) {
            val request = createOrdersPagingRequest(pageNo, pageSize, status, storeId, merchantId)
            service.getOrders(request).items?.map { it.toOrder() } ?: emptyList()
        }
    }

    override suspend fun getOrderDetails(
        orderId: Int,
        merchantId: String
    ): OrderDetails {
        return withContext(dispatcher) {
            service.getOrderDetails(orderId, merchantId).toOrderDetails()
        }
    }

    private fun createOrdersPagingRequest(
        pageNo: Int,
        pageSize: Int,
        status: Int,
        storeId: Int,
        merchantId: String
    ): OrdersPagingRequest {
        return OrdersPagingRequest(
            pageSize = pageSize,
            pageNo = pageNo,
            status = status,
            storeId = storeId,
            merchantId = merchantId
        )
    }
}