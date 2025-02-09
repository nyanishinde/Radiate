package com.example.radiate

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.E

private lateinit var recyclerView: RecyclerView
private lateinit var eventAdapter:EventAdapter
private val eventList = mutableListOf<DCUpcomingEventsItem>()

class UpcomingEvents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming_events)

        //Setting up toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbarEvents)
        setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //setting the back button on toolbar

        //Setting up recyclerView
        recyclerView = findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Initializing adapter and setting it on the recyclerView
        eventAdapter = EventAdapter(eventList)
        recyclerView.adapter = eventAdapter

        //Inserting data in eventList
        insertEventData()
    }

    private fun insertEventData() {
        val sampleData = listOf(
            DCUpcomingEventsItem("10","Board Meeting","10:00 AM"),
            DCUpcomingEventsItem("12","Team Meeting","10:00 AM"),
            DCUpcomingEventsItem("13","Group Meeting","10:00 AM"),
            DCUpcomingEventsItem("15","Friends Meeting","10:00 AM"),
            DCUpcomingEventsItem("18","get to gather","10:00 AM"),
            DCUpcomingEventsItem("20","Wedding anniversary","10:00 AM"),
            DCUpcomingEventsItem("22","Party","10:00 AM"),
            DCUpcomingEventsItem("24","Diwali","10:00 AM"),
            DCUpcomingEventsItem("26","Holi","10:00 AM"),
            DCUpcomingEventsItem("29","Navratri","10:00 AM")
        )
        eventList.addAll(sampleData)
        eventAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}