package com.example.radiate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import org.w3c.dom.Text

class ReminderDialogFragment:DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.popup_activity_reminder,container,false)

        //Initializing all view and their id's
        val reminderTitle = view.findViewById<EditText>(R.id.edtReminderTitle)
        val reminderDescription = view.findViewById<EditText>(R.id.edtReminderDescription)
        val reminderDate = view.findViewById<EditText>(R.id.edtReminderDate)
        val reminderTime = view.findViewById<EditText>(R.id.edtReminderTime)
        val repeat = view.findViewById<Spinner>(R.id.spinnerRepeat)
        val importance = view.findViewById<Spinner>(R.id.spinnerImportance)
        val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
        val btnDone = view.findViewById<TextView>(R.id.btnDone)
        return view
    }

}