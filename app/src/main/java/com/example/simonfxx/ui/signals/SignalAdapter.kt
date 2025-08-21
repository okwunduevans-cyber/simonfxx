package com.example.simonfxx.ui.signals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simonfxx.R
import com.example.simonfxx.model.Signal

class SignalAdapter(
    private val onClick: (Signal) -> Unit
) : ListAdapter<Signal, SignalAdapter.VH>(DIFF) {

    object DIFF : DiffUtil.ItemCallback<Signal>() {
        override fun areItemsTheSame(oldItem: Signal, newItem: Signal) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Signal, newItem: Signal) = oldItem == newItem
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val pair: TextView = view.findViewById(R.id.pair)
        val direction: TextView = view.findViewById(R.id.direction)
        val entry: TextView = view.findViewById(R.id.entry)
        val tp: TextView = view.findViewById(R.id.tp)
        val sl: TextView = view.findViewById(R.id.sl)
        val status: TextView = view.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_signal, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val s = getItem(position)

        holder.pair.text = s.pair
        holder.direction.text = s.direction
        holder.entry.text = "Entry: ${s.entry}"
        holder.tp.text = "TP: ${s.tp1}/${s.tp2}"
        holder.sl.text = "SL: ${s.sl}"
        holder.status.text = s.status

        // Color BUY green, SELL red (colors already in res/values/colors.xml)
        val ctx = holder.itemView.context
        val color = when (s.direction.uppercase()) {
            "BUY"  -> ContextCompat.getColor(ctx, R.color.buyGreen)
            "SELL" -> ContextCompat.getColor(ctx, R.color.sellRed)
            else   -> holder.direction.currentTextColor
        }
        holder.direction.setTextColor(color)

        holder.itemView.setOnClickListener { onClick(s) }
    }
}