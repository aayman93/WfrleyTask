package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.github.aayman93.wfrleytask.features.orders.domain.models.Order
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderDto(
    val cashierComment: Any?,
    val cashierUser: Any?,
    val couponCode: Any?,
    val createdDate: String?,
    val customerServiceComment: Any?,
    val customerServiceUser: UserDto?,
    val customerUser: UserDto?,
    val deliveryUser: Any?,
    val discountAmount: Int?,
    val fromPos: Boolean?,
    val grandTotal: Double?,
    val id: Int?,
    val idOnline: Any?,
    val inventory: InventoryDto?,
    val merchantId: String?,
    val operationNumber: Any?,
    val orderNumber: String?,
    val paymentMethod: Int?,
    val postponedDate: String?,
    val preparingOrderEmployeeUser: Any?,
    val priceAfterDiscountTotal: Double?,
    val purchasePoint: Int?,
    val serviceFee: Double?,
    val shippingAddresses: List<ShippingAddressDto>?,
    val shippingAmount: Double?,
    val shippingMethod: Int?,
    val startCompleteDate: Any?,
    val startShippingDate: Any?,
    val status: Int?,
    val syncSucceed: Boolean?,
    val totalRefunded: Int?,
    val updatedDate: String?
) {
    fun toOrder(): Order {
        return Order(
            id = this.id ?: -1,
            customerName = this.customerUser?.displayName.orEmpty(),
            orderDate = this.createdDate.orEmpty(),
            price = this.grandTotal ?: 0.0
        )
    }
}