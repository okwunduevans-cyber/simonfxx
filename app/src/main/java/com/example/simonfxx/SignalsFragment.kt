package com.example.simonfxx.ui.signals

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment

class SignalsFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View =
        TextView(requireContext()).apply {
            text = "Signals"
            textSize = 20f
            setPadding(32, 32, 32, 32)
        }
}