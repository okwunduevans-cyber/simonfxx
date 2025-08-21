package com.example.simonfxx.model

import java.time.ZonedDateTime

/**
 * Final trade outcome for a signal.
 */
data class Outcome(
    val result: String,            // "WIN" or "LOSE"
    val realizedPips: Double,      // e.g., 25.0
    val closeTime: ZonedDateTime   // when the trade was closed
)