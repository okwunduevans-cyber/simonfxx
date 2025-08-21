package com.example.simonfxx.model

import java.time.ZonedDateTime

/**
 * Model describing the health and provenance of an analyst/bot.
 */
data class BotInfo(
    val name: String,              // Bot or analyst name
    val version: String,           // e.g., "1.0.0"
    val type: String,              // "pdf_trained" / "online_ingest" / "analyst"
    val status: String,            // "ACTIVE" / "INACTIVE" / "ERROR"
    val lastHeartbeat: ZonedDateTime // Last time the bot reported healthy
)