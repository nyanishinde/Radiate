package com.example.radiate

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChecklistDialogFragment: DialogFragment() {

    private lateinit var todayDate:TextView
    private lateinit var taskCounter :TextView
    private lateinit var btnAddMoreTask : TextView
    private lateinit var btnCancel:TextView
    private lateinit var btnDone:TextView
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var adapter: CheckListAdapter
    private val taskListItems = mutableListOf(
        DCCheckListItem("Study",false),
        DCCheckListItem("Meetings",false),
        DCCheckListItem("Evening walk",false),
        DCCheckListItem("Morning workout",false),
        DCCheckListItem("Morning shower",false),
        DCCheckListItem("Meditation",false),
        DCCheckListItem("Dinner prep",false)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.popup_activity_today_task,container,false)

        //Initializing the views
        todayDate = view.findViewById(R.id.tvTodayDate)
        taskCounter = view.findViewById(R.id.tvTaskCounter)
        btnAddMoreTask = view.findViewById(R.id.tvAddMoreTask)
        btnCancel = view.findViewById(R.id.tvBtnCancel)
        btnDone= view.findViewById(R.id.tvBtnDone)

        //Initializing the recyclerview and adapter
        taskRecyclerView = view.findViewById(R.id.rvTodayTask)
        taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val checkItemsCount = taskListItems.count(){it.isChecked}
        val checkItemsTotalCount = taskListItems.size
        taskCounter.text = "$checkItemsCount/$checkItemsTotalCount" //setting initial count to zero

        adapter = CheckListAdapter(taskListItems){checked,total ->
            taskCounter.text = "$checked/$total" //changing counter on checking an item
        }
        taskRecyclerView.adapter=adapter

        //Handling the click event on add more task and opening dialog to add a new event
        btnAddMoreTask.setOnClickListener {
            openAddMoreTaskDialog()
        }

        btnDone.setOnClickListener {
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        return view
    }

    private fun openAddMoreTaskDialog() {
        val edtTaskName = EditText(requireContext())  //Creating a editText view
        edtTaskName.setHint("Enter task name")
        edtTaskName.setBackgroundColor(resources.getColor(R.color.white))
        edtTaskName.setTextColor(resources.getColor(R.color.black))
        edtTaskName.setPadding(40,20,40,20)

        AlertDialog.Builder(requireContext())
            .setTitle("Add new task") //Adding title to the dialog
            .setView(edtTaskName) //setting editView as a view in dialog
            .setPositiveButton("Add"){dialog,_ -> //Adding positive button and its response
                val taskName = edtTaskName.text.toString().trim()
                if (taskName.isNotEmpty()){
                    adapter.addTask(taskName)
                }
                dialog.dismiss() //close only addTask dialog
            }
            .setNegativeButton("Cancel"){dialog,_-> dialog.dismiss()} //Adding negative button and its response
            .show()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.85).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}