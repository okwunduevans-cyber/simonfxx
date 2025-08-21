package com.example.simonfxx.model

/**
 * Where a signal came from and how confident that source is.
 */
data class SignalSource(
    val providerId: String,   // e.g., "provider_x", "telegram_bot_123"
    val channel: String,      // "http" / "ws" / "telegram" / "email"
    val confidence: Double    // 0.0 .. 1.0 (e.g., 0.82)
)