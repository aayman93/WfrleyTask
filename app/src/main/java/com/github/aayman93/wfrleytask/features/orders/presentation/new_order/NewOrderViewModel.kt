package com.github.aayman93.wfrleytask.features.orders.presentation.new_order

import androidx.lifecycle.ViewModel
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderProduct
import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewOrderViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(NewOrderState())
    val state = _state.asStateFlow()


    fun onAddToOrder(product: Product) {
        val isProductSelected = _state.value.selectedProducts.any { it.product.id == product.id }
        val updatedList = if (isProductSelected) {
            _state.value.selectedProducts.map {
                if (it.product.id == product.id) {
                    it.copy(quantity = it.quantity.plus(1))
                } else {
                    it
                }
            }
        } else {
            _state.value.selectedProducts.plus(
                OrderProduct(quantity = 1.0, product = product)
            )
        }
        _state.update { it.copy(selectedProducts = updatedList) }
    }

    fun onRemoveFromOrder(product: Product) {
        val orderProduct = _state.value.selectedProducts.find { it.product.id == product.id }
        if (orderProduct == null) return
        val updatedList = if (orderProduct.quantity > 1) {
            _state.value.selectedProducts.map {
                if (it.product.id == product.id) {
                    it.copy(quantity = it.quantity.minus(1))
                } else {
                    it
                }
            }
        } else {
            _state.value.selectedProducts.filterNot { it.product.id == product.id }
        }
        _state.update { it.copy(selectedProducts = updatedList) }
    }

}