package com.example.simonfxx.data

import com.example.simonfxx.model.Signal
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID

/**
 * Temporary in-memory demo data for Sprint-1.
 * Later we'll swap this for Room/Network.
 */
object SignalsRepo {
    fun demoSignals(): List<Signal> {
        val now = ZonedDateTime.now(ZoneId.of("UTC"))
        return listOf(
            Signal(
                id = UUID.randomUUID().toString(),
                pair = "EURUSD",
                direction = "BUY",
                entry = 1.0850,
                tp1 = 1.0870,
                tp2 = 1.0900,
                sl = 1.0820,
                tier = "MAJOR",
                status = "PENDING",
                createdAt = now.minusMinutes(5),
                tradeTime = now.plusMinutes(15),
                note = "London session setup"
            ),
            Signal(
                id = UUID.randomUUID().toString(),
                pair = "GBPUSD",
                direction = "SELL",
                entry = 1.2650,
                tp1 = 1.2620,
                tp2 = 1.2590,
                sl = 1.2680,
                tier = "MAJOR",
                status = "APPROVED",
                createdAt = now.minusMinutes(20),
                tradeTime = now.plusMinutes(10),
                note = null
            ),
            Signal(
                id = UUID.randomUUID().toString(),
                pair = "XAUUSD",
                direction = "BUY",
                entry = 2400.0,
                tp1 = 2406.0,
                tp2 = 2412.0,
                sl = 2392.0,
                tier = "MINOR",
                status = "PENDING",
                createdAt = now.minusMinutes(2),
                tradeTime = now.plusMinutes(25),
                note = "Volatility watch"
            )
        )
    }
}