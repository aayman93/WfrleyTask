package com.github.aayman93.wfrleytask.features.orders.data.data_source

import com.github.aayman93.wfrleytask.features.orders.data.models.requests.OrdersPagingRequest
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
}