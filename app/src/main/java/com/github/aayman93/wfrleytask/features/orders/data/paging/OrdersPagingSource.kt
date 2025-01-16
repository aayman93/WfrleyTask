package com.github.aayman93.wfrleytask.features.orders.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.repository.OrdersRepository

class OrdersPagingSource(
    private val repository: OrdersRepository,
    private val pageSize: Int,
    private val status: Int,
    private val storeId: Int,
    private val merchantId: String
) : PagingSource<Int, Order>() {

    override fun getRefreshKey(state: PagingState<Int, Order>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        return try {
            val page = params.key ?: 1
            val orders = repository.getOrders(
                pageNo = page,
                pageSize = pageSize,
                status = status,
                storeId = storeId,
                merchantId = merchantId
            )
            LoadResult.Page(
                data = orders,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (orders.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}