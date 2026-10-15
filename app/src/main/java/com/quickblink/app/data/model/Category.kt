package com.quickblink.app.data.model

data class Category(
    val id: String,
    val name: String,
    val subtitle: String = "",
    val iconEmoji: String, // Instant lightweight visual without network latency
    val imageUrl: String = "",
    val badge: String? = null // e.g. "OFFERS", "NEW"
)
