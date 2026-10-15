package com.quickblink.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.quickblink.app.data.model.Product
import com.quickblink.app.ui.components.CategoryChipRow
import com.quickblink.app.ui.components.FloatingCartPill
import com.quickblink.app.ui.components.InstamartHeader
import com.quickblink.app.ui.components.ProductCard
import com.quickblink.app.ui.components.SearchBarComponent
import com.quickblink.app.ui.theme.BackgroundLight
import com.quickblink.app.ui.theme.InstamartPurple
import com.quickblink.app.ui.theme.SwiggyOrange
import com.quickblink.app.ui.theme.TextPrimary
import com.quickblink.app.ui.theme.TextSecondary
import com.quickblink.app.ui.viewmodel.CartViewModel
import com.quickblink.app.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    cartViewModel: CartViewModel,
    onViewCartClick: () -> Unit
) {
    val homeState by homeViewModel.uiState.collectAsState()
    val cartState by cartViewModel.uiState.collectAsState()

    // Map cart items by productId for instant O(1) quantity lookups
    val cartQuantities = cartState.items.associate { it.productId to it.quantity }

    Scaffold(
        bottomBar = {
            FloatingCartPill(
                itemCount = cartState.totalCount,
                totalPrice = cartState.grandTotal,
                totalSavings = cartState.totalSavings,
                onViewCartClick = onViewCartClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundLight)
                .padding(paddingValues)
        ) {
            // Header with Delivery ETA
            InstamartHeader()

            // Search Bar & Filter
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                SearchBarComponent(
                    query = homeState.searchQuery,
                    onQueryChange = { homeViewModel.updateSearchQuery(it) }
                )
            }

            // Category Chips Row
            CategoryChipRow(
                categories = homeState.categories,
                selectedCategoryId = homeState.selectedCategoryId,
                onCategorySelected = { homeViewModel.selectCategory(it) }
            )

            // Product Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 80.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // Promo Banner
                item(span = { GridItemSpan(2) }) {
                    PromoBannerCard()
                }

                // Section Header
                item(span = { GridItemSpan(2) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (homeState.searchQuery.isNotEmpty()) "Search Results" else "Trending Near You",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                        Text(
                            text = "${homeState.products.size} Items",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary
                        )
                    }
                }

                // Product items
                items(homeState.products, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        cartQuantity = cartQuantities[product.id] ?: 0,
                        onAddToCart = { cartViewModel.addToCart(it) },
                        onDecrement = { cartViewModel.decrementQuantity(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun PromoBannerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            InstamartPurple,
                            SwiggyOrange
                        )
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalOffer,
                            contentDescription = "Offer",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "INSTAMART SUPER SAVER",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold),
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Up to 40% OFF on Daily Staples",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                }
                Text(
                    text = "⚡ 11m",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.White
                )
            }
        }
    }
}
