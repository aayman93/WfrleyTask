package com.github.aayman93.wfrleytask.features.orders.data.models.dto

import com.github.aayman93.wfrleytask.features.orders.domain.models.Product
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    val additionalBarcode: String?,
    val alwaysAvailable: Boolean?,
    val barCode: String?,
    val childQuantity: Any?,
    val id: Int?,
    val image: String?,
    val isAvailable: Boolean?,
    val merchantProducts: List<MerchantProductDto>?,
    val name: String?,
    val price: Double?,
    val priceAfterDiscount: Double?,
    val productCategories: List<ProductCategoryDto>?,
    val productInvetories: List<ProductInventoryDto>?,
    val productType: Int?,
    val salableQuantity: Double?,
    val sku: String?,
    val subCategoryId: Int?
) {
    fun toProduct(): Product {
        return Product(
            id = id ?: -1,
            name = name.orEmpty(),
            price = price ?: 0.0,
            priceAfterDiscount = priceAfterDiscount ?: 0.0,
            saleableQuantity = salableQuantity ?: 0.0
        )
    }
}