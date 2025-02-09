package com.example.radiate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val itemList:List<DCUpcomingEventsItem>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvEventDate :TextView = itemView.findViewById(R.id.tvItemEventDate)
        val tvEventTitle : TextView = itemView.findViewById(R.id.tvItemEventTitle)
        val tvEventTime : TextView = itemView.findViewById(R.id.tvItemEventTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlist_upcomingevents,parent,false)
        return EventViewHolder(view)

    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvEventDate.text = item.eventDate
        holder.tvEventTitle.text = item.eventTitle
        holder.tvEventTime.text = item.eventTime
    }

    override fun getItemCount(): Int = itemList.size
}