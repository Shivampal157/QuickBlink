package com.quickblink.app

import android.app.Application
import com.quickblink.app.data.local.AppDatabase
import com.quickblink.app.data.repository.CartRepository
import com.quickblink.app.data.repository.ProductRepository

class QuickBlinkApp : Application() {

    lateinit var database: AppDatabase
        private set

    lateinit var cartRepository: CartRepository
        private set

    lateinit var productRepository: ProductRepository
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = AppDatabase.getDatabase(this)
        cartRepository = CartRepository(database.cartDao())
        productRepository = ProductRepository()
    }

    companion object {
        lateinit var instance: QuickBlinkApp
            private set
    }
}
