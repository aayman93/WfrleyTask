package com.github.aayman93.wfrleytask.features.orders.domain.use_cases

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateOrderUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(
        customerId: String,
        customerServiceUserId: String,
        storeId: Int,
        paymentDeliveryMethod: Int,
        postponedDate: String,
        addressId: Int,
        orderDeliveryMethod: Int,
        orderDetails: List<OrderDetail>
    ): Boolean {
        return repository.createOrder(
            customerId,
            customerServiceUserId,
            storeId,
            paymentDeliveryMethod,
            postponedDate,
            addressId,
            orderDeliveryMethod,
            orderDetails
        )
    }
}