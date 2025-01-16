package com.github.aayman93.wfrleytask.features.orders.domain.use_cases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.aayman93.wfrleytask.features.orders.data.paging.OrdersPagingSource
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetOrdersUseCase @Inject constructor(
    private val repository: OrdersRepository
) {
    operator fun invoke(
        pageSize: Int,
        status: Int,
        storeId: Int,
        merchantId: String
    ): Flow<PagingData<Order>> {
        return Pager(config = PagingConfig(pageSize = 10)) {
            OrdersPagingSource(
                repository = repository,
                pageSize = pageSize,
                status = status,
                storeId = storeId,
                merchantId = merchantId
            )
        }.flow
    }
}