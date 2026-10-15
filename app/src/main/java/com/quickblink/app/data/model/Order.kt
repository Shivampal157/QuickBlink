package com.quickblink.app.data.model

data class Order(
    val orderId: String,
    val items: List<CartItem>,
    val itemTotal: Double,
    val deliveryFee: Double,
    val handlingFee: Double,
    val tipAmount: Double,
    val grandTotal: Double,
    val totalSavings: Double,
    val deliveryAddress: String,
    val status: OrderStatus = OrderStatus.CONFIRMED,
    val etaMinutes: Int = 11,
    val deliveryPartnerName: String = "Ramesh Kumar",
    val deliveryPartnerRating: Float = 4.9f,
    val timestamp: Long = System.currentTimeMillis()
)

enum class OrderStatus {
    CONFIRMED,
    PACKING_AT_DARK_STORE,
    OUT_FOR_DELIVERY,
    DELIVERED
}
