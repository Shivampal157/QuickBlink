package com.quickblink.app.data.model

data class Product(
    val id: String,
    val name: String,
    val categoryId: String,
    val unit: String, // e.g. "500 ml", "1 kg", "Pack of 4"
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercent: Int = 0,
    val rating: Float = 4.5f,
    val ratingCount: Int = 120,
    val imageUrl: String,
    val isVeg: Boolean = true,
    val inStock: Boolean = true,
    val etaMinutes: Int = 11,
    val description: String = ""
) {
    val savings: Double
        get() = (originalPrice - discountedPrice).coerceAtLeast(0.0)
}
