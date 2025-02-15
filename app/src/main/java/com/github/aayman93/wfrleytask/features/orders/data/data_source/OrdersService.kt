package com.github.aayman93.wfrleytask.features.orders.data.data_source

import com.github.aayman93.wfrleytask.features.orders.data.models.dto.ProductDto
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.CreateOrderRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.OrdersPagingRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.requests.SearchProductsRequest
import com.github.aayman93.wfrleytask.features.orders.data.models.responses.CreateOrderResponse
import com.github.aayman93.wfrleytask.features.orders.data.models.responses.OrderDetailsResponse
import com.github.aayman93.wfrleytask.features.orders.data.models.responses.OrdersResponse
import com.github.aayman93.wfrleytask.utils.Constants.SUB_ROUTE
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrdersService {

    @POST("$SUB_ROUTE/GetAllWithPaging")
    suspend fun getOrders(
        @Body ordersPagingRequest: OrdersPagingRequest
    ): OrdersResponse

    @GET("$SUB_ROUTE/GetOrderDetails/{orderId}/{merchantId}")
    suspend fun getOrderDetails(
        @Path("orderId") orderId: Int,
        @Path("merchantId") merchantId: String
    ): OrderDetailsResponse

    @POST("$SUB_ROUTE/SearchProductsForCustomer")
    suspend fun searchProducts(
        @Body searchProductsRequest: SearchProductsRequest
    ): List<ProductDto>

    @POST("$SUB_ROUTE/CreateOrder")
    suspend fun createOrder(
        @Body createOrderRequest: CreateOrderRequest
    ): CreateOrderResponse
}