package com.example.radiate

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReminderFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_reminder, container, false)


        //Initializing ids of cards and widget
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarReminder)
        val iconProfile = view.findViewById<ImageView>(R.id.imgReminderProfile)
        val cardTodayTasks = view.findViewById<CardView>(R.id.cardTodayTasks)
        val cardWeeklyGoals = view.findViewById<CardView>(R.id.cardWeeklyGoals)
        val cardMonthlyGoals = view.findViewById<CardView>(R.id.cardMonthlyGoals)
        val cardUpcomingEvents = view.findViewById<CardView>(R.id.cardUpcomingEvents)
        val fabAddReminder = view.findViewById<FloatingActionButton>(R.id.fabAddReminder)
        iconProfile.setOnClickListener {
            Toast.makeText(context, "Profile",Toast.LENGTH_SHORT).show()
        }
        cardTodayTasks.setOnClickListener {
//            Toast.makeText(context, "Today's tasks", Toast.LENGTH_SHORT).show()
            val taskDialog = ChecklistDialogFragment()
            taskDialog.show(parentFragmentManager,"ChecklistDialog")
        }
        cardWeeklyGoals.setOnClickListener {
           Toast.makeText(context, "Weekly goals", Toast.LENGTH_SHORT).show()
        }
        cardMonthlyGoals.setOnClickListener {
            Toast.makeText(context, "Monthly goals", Toast.LENGTH_SHORT).show()
        }
        cardUpcomingEvents.setOnClickListener {
            val intent = Intent(requireContext(),UpcomingEvents::class.java)
            startActivity(intent)
        }
        fabAddReminder.setOnClickListener {
            Toast.makeText(context,"FAB Clicked",Toast.LENGTH_SHORT).show()
            val reminderDialog = ReminderDialogFragment()
            reminderDialog.show(parentFragmentManager,"ReminderDialog")
        }


        return view
    }

}