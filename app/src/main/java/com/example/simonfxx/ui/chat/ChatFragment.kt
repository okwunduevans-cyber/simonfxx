package com.example.simonfxx.ui.chat

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment

class ChatFragment : Fragment() {
    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View =
        TextView(requireContext()).apply {
            text = "Analyst Chat"
            textSize = 20f
            setPadding(32, 32, 32, 32)
        }
}