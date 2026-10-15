package com.quickblink.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.quickblink.app.ui.screens.CartBottomSheet
import com.quickblink.app.ui.screens.HomeScreen
import com.quickblink.app.ui.screens.OrderTrackingScreen
import com.quickblink.app.ui.theme.QuickBlinkTheme
import com.quickblink.app.ui.viewmodel.CartViewModel
import com.quickblink.app.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    ORDER_TRACKING
}

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModel.Factory(QuickBlinkApp.instance.cartRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickBlinkTheme {
                QuickBlinkMainContent(
                    homeViewModel = homeViewModel,
                    cartViewModel = cartViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickBlinkMainContent(
    homeViewModel: HomeViewModel,
    cartViewModel: CartViewModel
) {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
    var showCartSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    val cartState by cartViewModel.uiState.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
            AppScreen.HOME -> {
                HomeScreen(
                    homeViewModel = homeViewModel,
                    cartViewModel = cartViewModel,
                    onViewCartClick = { showCartSheet = true }
                )

                if (showCartSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showCartSheet = false },
                        sheetState = sheetState
                    ) {
                        CartBottomSheet(
                            cartViewModel = cartViewModel,
                            onCloseClick = {
                                coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showCartSheet = false
                                }
                            },
                            onOrderPlaced = {
                                coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                    showCartSheet = false
                                    currentScreen = AppScreen.ORDER_TRACKING
                                }
                            }
                        )
                    }
                }
            }

            AppScreen.ORDER_TRACKING -> {
                val order = cartState.placedOrder
                if (order != null) {
                    OrderTrackingScreen(
                        order = order,
                        onBackToHomeClick = {
                            cartViewModel.resetOrder()
                            currentScreen = AppScreen.HOME
                        }
                    )
                } else {
                    currentScreen = AppScreen.HOME
                }
            }
        }
    }
}
