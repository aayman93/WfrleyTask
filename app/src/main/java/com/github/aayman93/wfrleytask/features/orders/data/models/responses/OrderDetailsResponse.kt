package com.github.aayman93.wfrleytask.features.orders.data.models.responses

import com.github.aayman93.wfrleytask.features.orders.data.models.dto.InventoryDto
import com.github.aayman93.wfrleytask.features.orders.data.models.dto.OrderDetailDto
import com.github.aayman93.wfrleytask.features.orders.data.models.dto.ShippingAddressDto
import com.github.aayman93.wfrleytask.features.orders.data.models.dto.UserDto
import com.github.aayman93.wfrleytask.features.orders.domain.models.OrderDetails
import com.github.aayman93.wfrleytask.features.orders.domain.models.ShippingAddress
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderDetailsResponse(
    val barcode: String?,
    val cashierComment: Any?,
    val cashierUser: Any?,
    val couponCode: Any?,
    val createdDate: String?,
    val customerServiceComment: Any?,
    val customerServiceUser: UserDto?,
    val customerUser: UserDto?,
    val discountAmount: Double?,
    val grandTotal: Double?,
    val id: Int?,
    val idOnline: Any?,
    val inventory: InventoryDto?,
    val merchantName: String?,
    val operationNumber: Any?,
    val orderDetails: List<OrderDetailDto>?,
    val orderNumber: String?,
    val paymentDeliveryMethod: Int?,
    val paymentMethod: Int?,
    val postponedDate: String?,
    val priceAfterDiscountTotal: Double?,
    val receivedFromDelivery: Double?,
    val serviceFee: Double?,
    val shippingAddresses: List<ShippingAddressDto>?,
    val shippingAmount: Double?,
    val shippingMethod: Int?,
    val startCompleteDate: Any?,
    val startShippingDate: Any?,
    val status: Int?,
    val syncSucceed: Boolean?,
    val totalRefunded: Double?
) {
    fun toOrderDetails(): OrderDetails {
        return OrderDetails(
            id = id ?: -1,
            orderNumber = orderNumber.orEmpty(),
            orderDate = createdDate.orEmpty(),
            customerName = customerUser?.displayName.orEmpty(),
            discountAmount = discountAmount ?: 0.0,
            grandTotal = grandTotal ?: 0.0,
            items = orderDetails?.map { it.toOrderDetail() }.orEmpty(),
            shippingAddress = shippingAddresses?.firstOrNull()?.toShippingAddress()
                ?: ShippingAddress()
        )
    }
}