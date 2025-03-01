package com.example.radiate

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.util.*
import androidx.fragment.app.DialogFragment
class ReminderDialogFragment:DialogFragment() {

    @SuppressLint("DefaultLocale")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_activity_reminder,container,false)

        //Initializing all view and their id's
        val reminderTitle = view.findViewById<EditText>(R.id.edtReminderTitle)
        val remindexrDescription = view.findViewById<EditText>(R.id.edtReminderDescription)
        val reminderDate = view.findViewById<EditText>(R.id.edtReminderDate)
        val reminderTime = view.findViewById<EditText>(R.id.edtReminderTime)
        val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
        val btnDone = view.findViewById<TextView>(R.id.btnDone)
        val imgBtnCalender = view.findViewById<ImageButton>(R.id.imgBtnCalender)
        val imgBtnTime = view.findViewById<ImageButton>(R.id.imgBtnTime)


        // creating instance of calender to get current date and time
        val calender = Calendar.getInstance()

        //Setting current date as hint
        val defaultDate = String.format("%d/%d/%d",calender.get(Calendar.DAY_OF_MONTH),calender.get(Calendar.MONTH)+1,calender.get(Calendar.YEAR))
        reminderDate.setHint(defaultDate)
        // Date picker dialog
        imgBtnCalender.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                {_,year,month,dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    reminderDate.setText(selectedDate)
                },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        //Setting current time as hint
        val defaultTime = String.format("%02d:%02d",calender.get(Calendar.HOUR_OF_DAY),calender.get(Calendar.MINUTE))
        reminderTime.setHint(defaultTime)
        //Time picker dialog
        imgBtnTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                {_,hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    reminderTime.setText(selectedTime)
                },
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE),
                true
            )
            timePickerDialog.show()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnDone.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}