package com.example.radiate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class JournalFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var journalAdapter: JournalAdapter
    private val itemList = mutableListOf<DCJournalItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_journal, container, false)

        //Initializing recycler view
        recyclerView = view.findViewById(R.id.rvJournal)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Initialize Adapter
        journalAdapter = JournalAdapter(itemList)
        recyclerView.adapter = journalAdapter  //Setting adapter on the recyclerView

        //Inserting data
        insertJournalData()

        return view
    }

    private fun insertJournalData() {
        val sampleData = listOf(
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit"),
            DCJournalItems("28","July 2002","Sunday","One more day developing Santulit")
        )
        itemList.addAll(sampleData)
        journalAdapter.notifyDataSetChanged()  //notifying data changed to adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //add code here
    }
}