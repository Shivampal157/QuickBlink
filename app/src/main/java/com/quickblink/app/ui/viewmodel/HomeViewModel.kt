package com.quickblink.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quickblink.app.data.model.Category
import com.quickblink.app.data.model.Product
import com.quickblink.app.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
    val selectedCategoryId: String = "all",
    val searchQuery: String = "",
    val isLoading: Boolean = false
)

class HomeViewModel(
    private val productRepository: ProductRepository = ProductRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        productRepository.getCategories()
            .onEach { categories ->
                _uiState.update { it.copy(categories = categories) }
            }
            .launchIn(viewModelScope)

        loadProducts(null)
    }

    fun selectCategory(categoryId: String) {
        _uiState.update { it.copy(selectedCategoryId = categoryId, searchQuery = "") }
        loadProducts(categoryId)
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        productRepository.searchProducts(query)
            .onEach { filteredProducts ->
                _uiState.update { it.copy(products = filteredProducts) }
            }
            .launchIn(viewModelScope)
    }

    private fun loadProducts(categoryId: String?) {
        productRepository.getProducts(categoryId)
            .onEach { products ->
                _uiState.update { it.copy(products = products) }
            }
            .launchIn(viewModelScope)
    }
}
