package com.quickblink.app.data.repository

import com.quickblink.app.data.model.Category
import com.quickblink.app.data.model.Product

object SampleDataProvider {

    val categories = listOf(
        Category("all", "All", "Everything", "⚡"),
        Category("dairy", "Dairy & Breakfast", "Milk, Curd, Bread", "🥛", badge = "DAILY"),
        Category("fruits_veg", "Fruits & Veggies", "Farm fresh picks", "🍎", badge = "FRESH"),
        Category("snacks", "Snacks & Munchies", "Chips, Namkeen, Biscuits", "🍿", badge = "HOT"),
        Category("drinks", "Cold Drinks & Juices", "Soda, Energy Drinks", "🥤"),
        Category("instant", "Instant & Frozen", "Noodles, Pasta, Ready Meals", "🍜"),
        Category("sweet_tooth", "Chocolates & Ice Creams", "Desserts & Sweets", "🍫")
    )

    val products = listOf(
        // Dairy & Breakfast
        Product(
            id = "d1",
            name = "Amul Taaza Homogenised Toned Milk",
            categoryId = "dairy",
            unit = "500 ml",
            originalPrice = 28.0,
            discountedPrice = 27.0,
            discountPercent = 4,
            rating = 4.8f,
            ratingCount = 3400,
            imageUrl = "https://images.unsplash.com/photo-1550583724-b2692b85b150?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Pasteurized toned milk with 3.0% fat and 8.5% SNF. Perfect for daily tea, coffee, and breakfast cereals."
        ),
        Product(
            id = "d2",
            name = "Nandini Fresh Curd / Dahi",
            categoryId = "dairy",
            unit = "400 g",
            originalPrice = 30.0,
            discountedPrice = 28.0,
            discountPercent = 7,
            rating = 4.7f,
            ratingCount = 1890,
            imageUrl = "https://images.unsplash.com/photo-1571212515416-fef01fc43637?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Thick, creamy and delicious curd made from high-quality toned milk."
        ),
        Product(
            id = "d3",
            name = "Modern 100% Whole Wheat Bread",
            categoryId = "dairy",
            unit = "400 g",
            originalPrice = 50.0,
            discountedPrice = 45.0,
            discountPercent = 10,
            rating = 4.6f,
            ratingCount = 850,
            imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Rich in dietary fiber and essential nutrients. Baked fresh daily."
        ),
        Product(
            id = "d4",
            name = "Amul Salted Butter",
            categoryId = "dairy",
            unit = "100 g",
            originalPrice = 60.0,
            discountedPrice = 56.0,
            discountPercent = 7,
            rating = 4.9f,
            ratingCount = 5200,
            imageUrl = "https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Utterly butterly delicious butter made with fresh cream."
        ),

        // Fruits & Veggies
        Product(
            id = "fv1",
            name = "Farm Fresh Onions (Pyaaz)",
            categoryId = "fruits_veg",
            unit = "1 kg",
            originalPrice = 45.0,
            discountedPrice = 34.0,
            discountPercent = 24,
            rating = 4.4f,
            ratingCount = 4100,
            imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Directly sourced from trusted farmers. Crisp, firm and pungent red onions."
        ),
        Product(
            id = "fv2",
            name = "Hybrid Fresh Red Tomatoes",
            categoryId = "fruits_veg",
            unit = "500 g",
            originalPrice = 30.0,
            discountedPrice = 19.0,
            discountPercent = 36,
            rating = 4.3f,
            ratingCount = 2800,
            imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Juicy, ripe and farm-fresh tomatoes with natural sweetness."
        ),
        Product(
            id = "fv3",
            name = "Shimla Crisp Apples (Royal Gala)",
            categoryId = "fruits_veg",
            unit = "4 pcs (Approx 600g)",
            originalPrice = 160.0,
            discountedPrice = 129.0,
            discountPercent = 19,
            rating = 4.7f,
            ratingCount = 920,
            imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Sweet, aromatic, and crispy red apples picked directly from Himachal orchards."
        ),
        Product(
            id = "fv4",
            name = "Fresh Tender Coconut (Nariyal Paani)",
            categoryId = "fruits_veg",
            unit = "1 pc",
            originalPrice = 70.0,
            discountedPrice = 55.0,
            discountPercent = 21,
            rating = 4.8f,
            ratingCount = 1450,
            imageUrl = "https://images.unsplash.com/photo-1544378730-8b5104b18790?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Naturally refreshing and loaded with electrolytes. Chilled and cut to order."
        ),

        // Snacks & Munchies
        Product(
            id = "sn1",
            name = "Lay's American Style Cream & Onion Chips",
            categoryId = "snacks",
            unit = "73 g",
            originalPrice = 30.0,
            discountedPrice = 28.0,
            discountPercent = 6,
            rating = 4.8f,
            ratingCount = 7600,
            imageUrl = "https://images.unsplash.com/photo-1566478989037-eec170784d0b?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Crispy potato chips seasoned with a blend of sour cream and aromatic herbs."
        ),
        Product(
            id = "sn2",
            name = "Haldiram's Nagpur Aloo Bhujia",
            categoryId = "snacks",
            unit = "200 g",
            originalPrice = 65.0,
            discountedPrice = 55.0,
            discountPercent = 15,
            rating = 4.9f,
            ratingCount = 4300,
            imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Crispy spiced potato sticks blended with mint and secret Rajasthani spices."
        ),
        Product(
            id = "sn3",
            name = "Doritos Cheese Supreme Nacho Chips",
            categoryId = "snacks",
            unit = "82 g",
            originalPrice = 50.0,
            discountedPrice = 45.0,
            discountPercent = 10,
            rating = 4.6f,
            ratingCount = 2100,
            imageUrl = "https://images.unsplash.com/photo-1585238342024-78d387f4a707?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Crunchy corn chips loaded with melted cheddar cheese flavor."
        ),

        // Drinks
        Product(
            id = "dr1",
            name = "Coca-Cola Original Taste Can",
            categoryId = "drinks",
            unit = "300 ml",
            originalPrice = 40.0,
            discountedPrice = 36.0,
            discountPercent = 10,
            rating = 4.9f,
            ratingCount = 8900,
            imageUrl = "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "The classic, crisp, and refreshing taste of Coca-Cola."
        ),
        Product(
            id = "dr2",
            name = "Red Bull Energy Drink",
            categoryId = "drinks",
            unit = "250 ml",
            originalPrice = 125.0,
            discountedPrice = 115.0,
            discountPercent = 8,
            rating = 4.7f,
            ratingCount = 3100,
            imageUrl = "https://images.unsplash.com/photo-1551024709-8f23befc6f87?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Vitalizes body and mind with high caffeine, taurine, and B-group vitamins."
        ),
        Product(
            id = "dr3",
            name = "Raw Pressery Coconut Water",
            categoryId = "drinks",
            unit = "200 ml",
            originalPrice = 65.0,
            discountedPrice = 55.0,
            discountPercent = 15,
            rating = 4.5f,
            ratingCount = 670,
            imageUrl = "https://images.unsplash.com/photo-1544378730-8b5104b18790?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "100% natural tender coconut water with no added sugar or preservatives."
        ),

        // Instant Food
        Product(
            id = "ins1",
            name = "Maggi 2-Minute Masala Instant Noodles",
            categoryId = "instant",
            unit = "Pack of 4 (280 g)",
            originalPrice = 64.0,
            discountedPrice = 58.0,
            discountPercent = 9,
            rating = 4.9f,
            ratingCount = 12500,
            imageUrl = "https://images.unsplash.com/photo-1612927601601-6638404737ce?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "India's favorite instant noodles infused with 10 roasted aromatic spices."
        ),
        Product(
            id = "ins2",
            name = "Knorr Classic Sweet Corn Veg Soup",
            categoryId = "instant",
            unit = "43 g",
            originalPrice = 40.0,
            discountedPrice = 35.0,
            discountPercent = 12,
            rating = 4.4f,
            ratingCount = 890,
            imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Restaurant-style rich sweet corn soup ready in just 3 minutes."
        ),

        // Chocolates & Ice Creams
        Product(
            id = "sw1",
            name = "Cadbury Dairy Milk Silk Chocolate",
            categoryId = "sweet_tooth",
            unit = "150 g",
            originalPrice = 180.0,
            discountedPrice = 159.0,
            discountPercent = 12,
            rating = 4.9f,
            ratingCount = 6200,
            imageUrl = "https://images.unsplash.com/photo-1549007994-cb92caebd54b?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Creamier, smoother, and silkier chocolate that melts effortlessly in your mouth."
        ),
        Product(
            id = "sw2",
            name = "Kwality Walls Cornetto Double Chocolate",
            categoryId = "sweet_tooth",
            unit = "105 ml",
            originalPrice = 50.0,
            discountedPrice = 45.0,
            discountPercent = 10,
            rating = 4.8f,
            ratingCount = 3100,
            imageUrl = "https://images.unsplash.com/photo-1563805042-7684c019e1cb?auto=format&fit=crop&w=500&q=80",
            isVeg = true,
            description = "Crispy wafer cone loaded with rich chocolate ice cream and a solid chocolate tip."
        )
    )
}
