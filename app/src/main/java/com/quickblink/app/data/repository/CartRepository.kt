package com.quickblink.app.data.repository

import com.quickblink.app.data.local.CartDao
import com.quickblink.app.data.model.CartItem
import com.quickblink.app.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepository(private val cartDao: CartDao) {

    val cartItems: Flow<List<CartItem>> = cartDao.getAllCartItems()

    val totalItemCount: Flow<Int> = cartDao.getTotalItemCount().map { it ?: 0 }

    val totalCartValue: Flow<Double> = cartDao.getTotalCartValue().map { it ?: 0.0 }

    suspend fun addToCart(product: Product) {
        val existing = cartDao.getCartItem(product.id)
        if (existing != null) {
            cartDao.update(existing.copy(quantity = existing.quantity + 1, updatedTimestamp = System.currentTimeMillis()))
        } else {
            val newItem = CartItem(
                productId = product.id,
                name = product.name,
                unit = product.unit,
                unitPrice = product.discountedPrice,
                originalPrice = product.originalPrice,
                quantity = 1,
                imageUrl = product.imageUrl,
                isVeg = product.isVeg
            )
            cartDao.insertOrUpdate(newItem)
        }
    }

    suspend fun decrementQuantity(productId: String) {
        val existing = cartDao.getCartItem(productId) ?: return
        if (existing.quantity > 1) {
            cartDao.update(existing.copy(quantity = existing.quantity - 1, updatedTimestamp = System.currentTimeMillis()))
        } else {
            cartDao.deleteByProductId(productId)
        }
    }

    suspend fun removeFromCart(productId: String) {
        cartDao.deleteByProductId(productId)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    fun calculateDeliveryFee(itemTotal: Double): Double {
        return if (itemTotal >= 199.0 || itemTotal == 0.0) 0.0 else 25.0
    }

    fun getHandlingFee(): Double = 4.0
}
