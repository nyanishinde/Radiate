package com.example.radiate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CheckListAdapter(
    private val items:MutableList<DCCheckListItem>,
    private val updateCounter:(Int,Int) -> Unit
):RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

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
        holder.checkBox.text = item.text
        holder.checkBox.isChecked = item.isChecked

        holder.checkBox.setOnCheckedChangeListener(null)

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            reOrderList()
        }
    }

    private fun reOrderList() {
        items.sortBy { it.isChecked }
        notifyDataSetChanged()
        updateCounter(items.count { it.isChecked },items.size)
    }

    fun addTask(taskName:String){
        items.add(DCCheckListItem(taskName,false))
        notifyDataSetChanged()
        reOrderList()
    }

}