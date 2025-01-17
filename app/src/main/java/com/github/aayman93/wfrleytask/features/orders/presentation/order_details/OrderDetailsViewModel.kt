package com.github.aayman93.wfrleytask.features.orders.presentation.order_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.aayman93.wfrleytask.features.orders.domain.use_cases.GetOrderDetailsUseCase
import com.github.aayman93.wfrleytask.navigation.OrderDetailsRoute
import com.github.aayman93.wfrleytask.utils.Constants.MERCHANT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getOrderDetailsUseCase: GetOrderDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = savedStateHandle.toRoute<OrderDetailsRoute>()

    private val _state = MutableStateFlow(OrderDetailsState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<OrderDetailsEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _state.update { it.copy(isLoading = false) }
            throwable.printStackTrace()
            throwable.localizedMessage?.let {
                _eventChannel.send(OrderDetailsEvent.OnError(it))
            }
        }
    }

    init {
        getOrderDetails(args.id)
    }



    private fun getOrderDetails(orderId: Int) {
        viewModelScope.launch(errorHandler) {
            _state.update { it.copy(isLoading = true) }

            val result = getOrderDetailsUseCase(orderId, MERCHANT_ID)

            _state.update { it.copy(isLoading = false, orderDetails = result) }
        }
    }
}