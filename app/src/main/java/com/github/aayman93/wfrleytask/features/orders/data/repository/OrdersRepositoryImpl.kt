package com.github.aayman93.wfrleytask.features.orders.data.repository

import com.github.aayman93.wfrleytask.features.orders.data.data_source.OrdersService
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.CreateOrderRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.OrderDetailRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.OrdersPagingRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.SearchProductsRequest
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
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

    override suspend fun searchProductsForCustomer(
        name: String,
        storeId: Int,
        merchantId: String
    ): List<Product> {
        return withContext(dispatcher) {
            val request = createSearchProductsRequest(name, storeId, merchantId)
            service.searchProducts(request).map { it.toProduct() }
        }
    }

    override suspend fun createOrder(
        customerId: String,
        customerServiceUserId: String,
        storeId: Int,
        paymentDeliveryMethod: Int,
        postponedDate: String,
        addressId: Int,
        orderDeliveryMethod: Int,
        orderDetails: List<OrderDetail>
    ): Boolean {
        return withContext(dispatcher) {
            val request = createCreateOrderRequest(
                customerId,
                customerServiceUserId,
                storeId,
                paymentDeliveryMethod,
                postponedDate,
                addressId,
                orderDeliveryMethod,
                orderDetails
            )
            service.createOrder(request).id != null
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

    private fun createSearchProductsRequest(
        name: String,
        storeId: Int,
        merchantId: String
    ) : SearchProductsRequest {
        return SearchProductsRequest(
            name = name,
            storeId = storeId,
            merchantId = merchantId
        )
    }

    private fun createCreateOrderRequest(
        customerId: String,
        customerServiceUserId: String,
        storeId: Int,
        paymentDeliveryMethod: Int,
        postponedDate: String,
        addressId: Int,
        orderDeliveryMethod: Int,
        orderDetails: List<OrderDetail>
    ): CreateOrderRequest {
        return CreateOrderRequest(
            customerId = customerId,
            customerServiceUserId = customerServiceUserId,
            storeId = storeId,
            paymentDeliveryMethod = paymentDeliveryMethod,
            postponedDate = postponedDate,
            addressId = addressId,
            priceAfterDiscountTotal = orderDetails.sumOf { it.rowPriceAfterDiscount },
            orderDeliveryMethod = orderDeliveryMethod,
            orderDetails = orderDetails.map {
                OrderDetailRequest(
                    quantity = it.quantity,
                    rowTotal = it.rowTotal,
                    rowPriceAfterDiscount = it.rowPriceAfterDiscount,
                    porductId = it.product.id
                )
            }
        )
    }
}