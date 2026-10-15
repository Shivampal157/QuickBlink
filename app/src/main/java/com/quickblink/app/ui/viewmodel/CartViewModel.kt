package com.quickblink.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.quickblink.app.data.model.CartItem
import com.quickblink.app.data.model.Order
import com.quickblink.app.data.model.OrderStatus
import com.quickblink.app.data.model.Product
import com.quickblink.app.data.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val totalCount: Int = 0,
    val itemTotal: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val handlingFee: Double = 4.0,
    val tipAmount: Double = 20.0,
    val grandTotal: Double = 0.0,
    val totalSavings: Double = 0.0,
    val placedOrder: Order? = null
)

class CartViewModel(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _tipAmount = MutableStateFlow(20.0)
    private val _placedOrder = MutableStateFlow<Order?>(null)

    val uiState: StateFlow<CartUiState> = combine(
        cartRepository.cartItems,
        _tipAmount,
        _placedOrder
    ) { items, tip, order ->
        val totalCount = items.sumOf { it.quantity }
        val itemTotal = items.sumOf { it.totalPrice }
        val totalSavings = items.sumOf { it.totalSavings }
        val deliveryFee = cartRepository.calculateDeliveryFee(itemTotal)
        val handlingFee = if (itemTotal > 0) cartRepository.getHandlingFee() else 0.0
        val grandTotal = if (itemTotal > 0) itemTotal + deliveryFee + handlingFee + tip else 0.0

        CartUiState(
            items = items,
            totalCount = totalCount,
            itemTotal = itemTotal,
            deliveryFee = deliveryFee,
            handlingFee = handlingFee,
            tipAmount = tip,
            grandTotal = grandTotal,
            totalSavings = totalSavings,
            placedOrder = order
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CartUiState()
    )

    fun addToCart(product: Product) {
        viewModelScope.launch {
            cartRepository.addToCart(product)
        }
    }

    fun decrementQuantity(productId: String) {
        viewModelScope.launch {
            cartRepository.decrementQuantity(productId)
        }
    }

    fun removeFromCart(productId: String) {
        viewModelScope.launch {
            cartRepository.removeFromCart(productId)
        }
    }

    fun selectTip(tip: Double) {
        _tipAmount.value = tip
    }

    fun placeOrder(address: String = "Koramangala 4th Block, Bengaluru"): Order {
        val currentState = uiState.value
        val order = Order(
            orderId = "SB-" + UUID.randomUUID().toString().take(8).uppercase(),
            items = currentState.items,
            itemTotal = currentState.itemTotal,
            deliveryFee = currentState.deliveryFee,
            handlingFee = currentState.handlingFee,
            tipAmount = currentState.tipAmount,
            grandTotal = currentState.grandTotal,
            totalSavings = currentState.totalSavings,
            deliveryAddress = address,
            status = OrderStatus.CONFIRMED,
            etaMinutes = 11,
            timestamp = System.currentTimeMillis()
        )

        _placedOrder.value = order

        viewModelScope.launch {
            cartRepository.clearCart()
        }

        return order
    }

    fun resetOrder() {
        _placedOrder.value = null
    }

    class Factory(private val cartRepository: CartRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartViewModel(cartRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
