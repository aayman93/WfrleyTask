package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderProduct
import com.github.aayman93.wfrleytask.features.orders.domain.use_cases.CreateOrderUseCase
import com.github.aayman93.wfrleytask.utils.Constants.ADDRESS_ID
import com.github.aayman93.wfrleytask.utils.Constants.CUSTOMER_ID
import com.github.aayman93.wfrleytask.utils.Constants.CUSTOMER_SERVICE_USER_ID
import com.github.aayman93.wfrleytask.utils.Constants.ORDER_DELIVERY_METHOD
import com.github.aayman93.wfrleytask.utils.Constants.PAYMENT_DELIVERY_METHOD
import com.github.aayman93.wfrleytask.utils.Constants.POSTPONED_DATE
import com.github.aayman93.wfrleytask.utils.Constants.STORE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishOrderViewModel @Inject constructor(
    private val createOrderUseCase: CreateOrderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FinishOrderState())
    val state = _state.asStateFlow()

    private val _eventChannel = Channel<FinishOrderEvent>()
    val eventFlow = _eventChannel.receiveAsFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _state.update { it.copy(isSaving = false) }
            throwable.printStackTrace()
            throwable.localizedMessage?.let {
                _eventChannel.send(FinishOrderEvent.OnError(it))
            }
        }
    }

    fun prepareOrderItems(orderProducts: List<OrderProduct>) {
        if (_state.value.orderItems.isNotEmpty()) return
        _state.update { it.copy(isLoading = true) }
        val orderItems = orderProducts.map {
            it.toOrderDetail()
        }
        _state.update { it.copy(isLoading = false, orderItems = orderItems) }
    }

    fun onPaidAmountChanged(newValue: String) {
        _state.update { it.copy(paidAmount = newValue) }
    }

    fun onCustomerNameChanged(newValue: String) {
        _state.update { it.copy(customerName = newValue) }
    }

    fun onCustomerPhoneChanged(newValue: String) {
        _state.update { it.copy(customerPhone = newValue) }
    }

    fun onConfirmOrder() {
        viewModelScope.launch(errorHandler) {
            _state.update { it.copy(isSaving = true) }
            val result = createOrderUseCase(
                customerId = CUSTOMER_ID,
                customerServiceUserId = CUSTOMER_SERVICE_USER_ID,
                storeId = STORE_ID,
                paymentDeliveryMethod = PAYMENT_DELIVERY_METHOD,
                postponedDate = POSTPONED_DATE,
                addressId = ADDRESS_ID,
                orderDeliveryMethod = ORDER_DELIVERY_METHOD,
                orderDetails = _state.value.orderItems
            )
            _state.update { it.copy(isSaving = false) }
            if (result) {
                _state.update { it.copy(isDialogVisible = true) }
                delay(2000)
                onDialogDismissed()
            }
        }
    }

    fun onDialogDismissed() {
        if (_state.value.isDialogVisible) {
            _state.update { it.copy(isDialogVisible = false) }
            viewModelScope.launch {
                delay(100L)
                _eventChannel.send(FinishOrderEvent.OnOrderSuccess)
            }
        }
    }
}