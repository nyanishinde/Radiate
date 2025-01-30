package com.example.radiate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder
import org.w3c.dom.Text

class JournalAdapter(private val itemList:List<DCJournalItems>) : RecyclerView.Adapter<JournalAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val date :TextView = itemView.findViewById(R.id.tvDate)
        val monthAndYear : TextView = itemView.findViewById(R.id.tvMonthYearAndDay)
        val day : TextView = itemView.findViewById(R.id.tvDay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalAdapter.ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_journal,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.title.text = item.title
        holder.date.text = item.date
        holder.monthAndYear.text = item.monthAndYear
        holder.day.text = item.day
    }

    override fun getItemCount(): Int = itemList.size
}