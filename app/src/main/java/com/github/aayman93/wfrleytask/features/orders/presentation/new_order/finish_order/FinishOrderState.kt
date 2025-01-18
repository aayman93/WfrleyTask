package com.github.aayman93.wfrleytask.features.orders.presentation.new_order.finish_order

import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetail

data class FinishOrderState(
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isDialogVisible: Boolean = false,
    val orderItems: List<OrderDetail> = emptyList(),
    val paidAmount: String = "",
    val customerName: String = "",
    val customerPhone: String = ""
) {
    val isFinishOrderEnabled
        get() = paidAmount.isNotBlank() && customerName.isNotBlank() &&
                customerPhone.isNotBlank() && orderItems.isNotEmpty()
}
