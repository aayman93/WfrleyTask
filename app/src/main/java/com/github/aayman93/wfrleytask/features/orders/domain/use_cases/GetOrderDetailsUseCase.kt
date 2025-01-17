package com.github.aayman93.wfrleytask.features.orders.domain.use_cases

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetOrderDetailsUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(orderId: Int, merchantId: String): OrderDetails {
        return repository.getOrderDetails(orderId, merchantId)
    }
}