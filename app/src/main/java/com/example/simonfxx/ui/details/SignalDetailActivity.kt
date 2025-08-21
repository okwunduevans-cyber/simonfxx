package com.example.simonfxx.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simonfxx.R

class SignalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signal_detail)

        val title = findViewById<TextView>(R.id.title)
        val pair = findViewById<TextView>(R.id.pair)
        val direction = findViewById<TextView>(R.id.direction)
        val entry = findViewById<TextView>(R.id.entry)
        val tp = findViewById<TextView>(R.id.tp)
        val sl = findViewById<TextView>(R.id.sl)
        val status = findViewById<TextView>(R.id.status)

        // Pull fields from Intent extras (kept simple for now)
        val pairVal = intent.getStringExtra("pair") ?: "—"
        val directionVal = intent.getStringExtra("direction") ?: "—"
        val entryVal = intent.getStringExtra("entry") ?: "—"
        val tp1Val = intent.getStringExtra("tp1") ?: "—"
        val tp2Val = intent.getStringExtra("tp2") ?: "—"
        val slVal = intent.getStringExtra("sl") ?: "—"
        val statusVal = intent.getStringExtra("status") ?: "—"

        title.text = "Signal Details"
        pair.text = "Pair: $pairVal"
        direction.text = "Direction: $directionVal"
        entry.text = "Entry: $entryVal"
        tp.text = "TP: $tp1Val / $tp2Val"
        sl.text = "SL: $slVal"
        status.text = "Status: $statusVal"
    }
}