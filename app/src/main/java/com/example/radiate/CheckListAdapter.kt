package com.example.radiate

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class CheckListAdapter(
    private val items:MutableList<DCCheckListItem>,
    private val updateCounter:(Int,Int) -> Unit
):RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

    private val handler = Handler(Looper.getMainLooper())
    inner class CheckListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val checkBox:CheckBox = itemView.findViewById(R.id.checkBoxItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_checklist,parent,false)
        return CheckListViewHolder(item)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) {
        val item = items[position]

        holder.checkBox.setOnCheckedChangeListener(null)


        holder.checkBox.text = item.text
        holder.checkBox.isChecked = item.isChecked

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            handler.postDelayed({
                reOrderList()
            },100) // 100ms delay to allow RecyclerView to finish processing
        }
    }

    private fun reOrderList() {
        items.sortBy { it.isChecked }
        notifyDataSetChanged()
        updateCounter(items.count { it.isChecked },items.size)
    }

    fun addTask(taskName:String){
        items.add(DCCheckListItem(taskName,false))
        reOrderList()
    }

}