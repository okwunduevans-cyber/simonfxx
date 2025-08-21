package com.example.simonfxx.model

import java.time.ZonedDateTime

/**
 * Core trading signal model for the app.
 */
data class Signal(
    val id: String,
    val pair: String,          // e.g., "EURUSD"
    val direction: String,     // "BUY" or "SELL"
    val entry: Double,
    val tp1: Double,
    val tp2: Double,
    val sl: Double,
    val tier: String,          // "MAJOR" / "MINOR"
    val status: String,        // "PENDING" / "APPROVED" / "FILLED" / "EXPIRED"
    val createdAt: ZonedDateTime,
    val tradeTime: ZonedDateTime,
    val note: String? = null   // optional note field
)