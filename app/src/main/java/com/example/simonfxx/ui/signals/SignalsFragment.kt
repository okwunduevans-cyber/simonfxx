package com.example.simonfxx.ui.signals

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simonfxx.R
import com.example.simonfxx.data.SignalsRepo
import com.example.simonfxx.ui.details.SignalDetailActivity

class SignalsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_signals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<RecyclerView>(R.id.signalsList)
        val adapter = SignalAdapter { s ->
            // Pass a few fields to the detail screen (keep it lightweight for now)
            val it = Intent(requireContext(), SignalDetailActivity::class.java).apply {
                putExtra("pair", s.pair)
                putExtra("direction", s.direction)
                putExtra("entry", s.entry.toString())
                putExtra("tp1", s.tp1.toString())
                putExtra("tp2", s.tp2.toString())
                putExtra("sl", s.sl.toString())
                putExtra("status", s.status)
            }
            startActivity(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        list.adapter = adapter

        adapter.submitList(SignalsRepo.demoSignals())
    }
}