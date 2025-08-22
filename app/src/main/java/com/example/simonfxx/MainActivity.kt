package com.example.simonfxx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simonfxx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val key = BuildConfig.GEMINI_API_KEY
        binding.hello.text = if (key.isBlank()) {
            "Hello, SimonFXX!\n(GEMINI_API_KEY not set)"
        } else {
            "Hello, SimonFXX!\nAPI key present ✓"
        }
    }
}
