package com.github.aayman93.wfrleytask.features.orders.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.github.aayman93.wfrleytask.features.orders.domain.use_cases.GetOrdersUseCase
import com.github.aayman93.wfrleytask.utils.Constants.MERCHANT_ID
import com.github.aayman93.wfrleytask.utils.Constants.STORE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase
) : ViewModel() {

    private val _orders = MutableStateFlow<PagingData<Order>>(PagingData.empty())
    val orders = _orders
        .onStart {
            getOrders()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = PagingData.empty()
        )

    private fun getOrders() {
        val result = getOrdersUseCase(
            pageSize = 10,
            status = 0,
            storeId = STORE_ID,
            merchantId = MERCHANT_ID
        ).cachedIn(viewModelScope)
        viewModelScope.launch {
            _orders.emit(result.first())
        }
    }
}