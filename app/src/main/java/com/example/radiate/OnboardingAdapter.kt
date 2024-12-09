package com.example.radiate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnboardingAdapter(private val items:List<OnboardingItemsDC>)
    : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingAdapter.OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_pages,parent,false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class OnboardingViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(item: OnboardingItemsDC){
            itemView.
        }
    }
}