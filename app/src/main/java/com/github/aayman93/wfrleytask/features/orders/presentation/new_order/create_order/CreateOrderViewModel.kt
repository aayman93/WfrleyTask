package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.create_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aayman93.wfrleytask.features.orders.domain.use_cases.SearchProductsUseCase
import com.github.aayman93.wfrleytask.utils.Constants.MERCHANT_ID
import com.github.aayman93.wfrleytask.utils.Constants.STORE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreateOrderState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<CreateOrderEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    private var searchJob: Job? = null

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _state.update { it.copy(isLoading = false) }
            throwable.printStackTrace()
            throwable.localizedMessage?.let {
                _eventChannel.send(CreateOrderEvent.OnError("عذراً، لا يوجد منتجات بهذا الاسم"))
            }
        }
    }

    init {
        searchProducts()
    }

    private fun searchProducts(shouldDelay: Boolean = false) {
        searchJob = viewModelScope.launch(errorHandler) {
            if (shouldDelay) {
                delay(400L)
            }
            _state.update { it.copy(isLoading = true) }
            val result = searchProductsUseCase(
                name = _state.value.query,
                storeId = STORE_ID,
                merchantId = MERCHANT_ID
            )
            _state.update { it.copy(isLoading = false, searchProducts = result) }
        }
    }

    fun onQueryChanged(query: String) {
        _state.update { it.copy(query = query) }
        searchJob?.cancel()
        searchProducts(shouldDelay = true)
    }
}