package com.quickblink.app.data.repository

import com.quickblink.app.data.model.Category
import com.quickblink.app.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository {

    fun getCategories(): Flow<List<Category>> = flow {
        emit(SampleDataProvider.categories)
    }

    fun getProducts(categoryId: String? = null): Flow<List<Product>> = flow {
        val list = if (categoryId == null || categoryId == "all") {
            SampleDataProvider.products
        } else {
            SampleDataProvider.products.filter { it.categoryId == categoryId }
        }
        emit(list)
    }

    fun searchProducts(query: String): Flow<List<Product>> = flow {
        val trimmed = query.trim().lowercase()
        if (trimmed.isEmpty()) {
            emit(SampleDataProvider.products)
        } else {
            val results = SampleDataProvider.products.filter { product ->
                product.name.lowercase().contains(trimmed) ||
                        product.description.lowercase().contains(trimmed) ||
                        product.categoryId.lowercase().contains(trimmed)
            }
            emit(results)
        }
    }

    fun getProductById(id: String): Product? {
        return SampleDataProvider.products.find { it.id == id }
    }
}
