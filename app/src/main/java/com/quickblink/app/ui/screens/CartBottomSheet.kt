package com.quickblink.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quickblink.app.data.model.CartItem
import com.quickblink.app.ui.theme.AlertRedLight
import com.quickblink.app.ui.theme.BorderLight
import com.quickblink.app.ui.theme.DividerColor
import com.quickblink.app.ui.theme.FreshGreen
import com.quickblink.app.ui.theme.FreshGreenLight
import com.quickblink.app.ui.theme.GoldTip
import com.quickblink.app.ui.theme.GoldTipLight
import com.quickblink.app.ui.theme.InstamartPurple
import com.quickblink.app.ui.theme.SurfaceWhite
import com.quickblink.app.ui.theme.SwiggyOrange
import com.quickblink.app.ui.theme.TextPrimary
import com.quickblink.app.ui.theme.TextSecondary
import com.quickblink.app.ui.theme.TextTertiary
import com.quickblink.app.ui.theme.VegGreen
import com.quickblink.app.ui.viewmodel.CartViewModel

@Composable
fun CartBottomSheet(
    cartViewModel: CartViewModel,
    onCloseClick: () -> Unit,
    onOrderPlaced: () -> Unit
) {
    val cartState by cartViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F6FB))
            .padding(top = 8.dp)
    ) {
        // Drag Handle & Header
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(width = 40.dp, height = 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.Gray.copy(alpha = 0.3f))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "My Cart (${cartState.totalCount} items)",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ElectricBolt,
                        contentDescription = "Fast",
                        tint = SwiggyOrange,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Delivering in 11 Mins to Home",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = TextSecondary
                    )
                }
            }

            IconButton(onClick = onCloseClick) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = TextPrimary
                )
            }
        }

        Divider(color = DividerColor)

        if (cartState.items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "🛒",
                        fontSize = 48.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Your Cart is Empty",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Explore categories to add fresh groceries!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                // Free Delivery Banner
                item {
                    val remainingForFreeDelivery = (199.0 - cartState.itemTotal).coerceAtLeast(0.0)
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        color = if (remainingForFreeDelivery == 0.0) FreshGreenLight else Color.White,
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (remainingForFreeDelivery == 0.0) Icons.Default.CheckCircle else Icons.Default.Savings,
                                    contentDescription = "Free delivery",
                                    tint = FreshGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (remainingForFreeDelivery == 0.0)
                                        "You have unlocked FREE Delivery!"
                                    else
                                        "Add ₹${remainingForFreeDelivery.toInt()} more for FREE Delivery",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = if (remainingForFreeDelivery == 0.0) FreshGreen else TextPrimary
                                )
                            }
                            if (remainingForFreeDelivery > 0.0) {
                                Spacer(modifier = Modifier.height(6.dp))
                                LinearProgressIndicator(
                                    progress = (cartState.itemTotal / 199.0).toFloat().coerceIn(0f, 1f),
                                    color = FreshGreen,
                                    trackColor = BorderLight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                )
                            }
                        }
                    }
                }

                // Cart Items List
                items(cartState.items, key = { it.productId }) { item ->
                    CartItemRow(
                        item = item,
                        onIncrement = {
                            val dummyProduct = com.quickblink.app.data.model.Product(
                                id = item.productId,
                                name = item.name,
                                categoryId = "",
                                unit = item.unit,
                                originalPrice = item.originalPrice,
                                discountedPrice = item.unitPrice,
                                imageUrl = item.imageUrl
                            )
                            cartViewModel.addToCart(dummyProduct)
                        },
                        onDecrement = { cartViewModel.decrementQuantity(item.productId) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Delivery Partner Tip Section
                item {
                    DeliveryTipSection(
                        selectedTip = cartState.tipAmount,
                        onTipSelected = { cartViewModel.selectTip(it) }
                    )
                }

                // Bill Details Breakdown
                item {
                    BillDetailsCard(cartState)
                }

                // Cancellation policy
                item {
                    Text(
                        text = "Orders once placed cannot be cancelled to maintain freshness.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextTertiary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            // Fixed Bottom Checkout Bar
            Surface(
                color = SurfaceWhite,
                shadowElevation = 16.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "TO PAY",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = TextTertiary
                        )
                        Text(
                            text = "₹${cartState.grandTotal.toInt()}",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = TextPrimary
                        )
                    }

                    Button(
                        onClick = {
                            cartViewModel.placeOrder()
                            onOrderPlaced()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = FreshGreen),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .height(48.dp)
                            .width(200.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Secure",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Slide to Pay",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CartItemRow(
    item: CartItem,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Veg Icon
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .border(1.5.dp, VegGreen, RoundedCornerShape(2.dp))
                        .padding(2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(VegGreen)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = TextPrimary,
                        maxLines = 1
                    )
                    Text(
                        text = item.unit,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "₹${item.totalPrice.toInt()}",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        color = TextPrimary
                    )
                }
            }

            // Stepper [- qty +]
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(FreshGreenLight)
                    .border(1.dp, FreshGreen, RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = onDecrement),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Decrease",
                        tint = FreshGreen,
                        modifier = Modifier.size(14.dp)
                    )
                }

                Text(
                    text = "${item.quantity}",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = FreshGreen,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .clickable(onClick = onIncrement),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increase",
                        tint = FreshGreen,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DeliveryTipSection(
    selectedTip: Double,
    onTipSelected: (Double) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Tip Your Delivery Partner",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
            )
            Text(
                text = "100% of the tip goes directly to your partner",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(10.0, 20.0, 30.0, 50.0).forEach { tip ->
                    val isSelected = selectedTip == tip
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onTipSelected(if (isSelected) 0.0 else tip) },
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSelected) GoldTipLight else Color(0xFFF9FAFB),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) GoldTip else BorderLight
                        )
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "₹${tip.toInt()}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                ),
                                color = if (isSelected) Color(0xFF8A5B00) else TextPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BillDetailsCard(state: com.quickblink.app.ui.viewmodel.CartUiState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderLight)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "Bill Details",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(10.dp))

            BillRow("Items Total", "₹${state.itemTotal.toInt()}")
            BillRow(
                label = "Delivery Partner Fee",
                value = if (state.deliveryFee == 0.0) "FREE" else "₹${state.deliveryFee.toInt()}",
                isHighlighted = state.deliveryFee == 0.0
            )
            BillRow("Handling Fee", "₹${state.handlingFee.toInt()}")
            if (state.tipAmount > 0) {
                BillRow("Delivery Partner Tip", "₹${state.tipAmount.toInt()}")
            }

            Divider(color = DividerColor, modifier = Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Grand Total",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = TextPrimary
                )
                Text(
                    text = "₹${state.grandTotal.toInt()}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = TextPrimary
                )
            }

            if (state.totalSavings > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    color = FreshGreenLight,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Yay! You saved ₹${state.totalSavings.toInt()} on this order",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = FreshGreen,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BillRow(label: String, value: String, isHighlighted: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isHighlighted) FreshGreen else TextPrimary
        )
    }
}
