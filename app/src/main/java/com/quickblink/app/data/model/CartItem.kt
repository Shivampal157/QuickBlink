package com.quickblink.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey
    val productId: String,
    val name: String,
    val unit: String,
    val unitPrice: Double,
    val originalPrice: Double,
    val quantity: Int,
    val imageUrl: String,
    val isVeg: Boolean = true,
    val updatedTimestamp: Long = System.currentTimeMillis()
) {
    val totalPrice: Double
        get() = unitPrice * quantity

    val totalSavings: Double
        get() = (originalPrice - unitPrice).coerceAtLeast(0.0) * quantity
}
