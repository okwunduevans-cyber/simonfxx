package com.example.simonfxx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.appbar.MaterialToolbar
import com.example.simonfxx.ui.signals.SignalsFragment
import com.example.simonfxx.ui.chat.ChatFragment
import com.example.simonfxx.ui.chart.ChartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hook up Material toolbar (Theme.NoActionBar means we provide our own)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Simonfxx"

        if (savedInstanceState == null) {
            supportFragmentManager.commit { replace(R.id.container, SignalsFragment()) }
        }

        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.setOnItemSelectedListener { item ->
            val (frag, title) = when (item.itemId) {
                R.id.tab_signals -> SignalsFragment() to "Signals"
                R.id.tab_chat    -> ChatFragment() to "Analyst Chat"
                R.id.tab_chart   -> ChartFragment() to "Market Chart"
                else             -> SignalsFragment() to "Signals"
            }
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.container, frag)
            }
            supportActionBar?.title = title
            true
        }
    }
}