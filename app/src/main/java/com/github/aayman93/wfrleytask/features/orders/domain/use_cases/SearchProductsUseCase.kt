package com.github.aayman93.wfrleytask.features.orders.domain.use_cases

import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchProductsUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    suspend operator fun invoke(
        name: String,
        storeId: Int,
        merchantId: String
    ): List<Product> {
        return repository.searchProductsForCustomer(name, storeId, merchantId)
    }
}