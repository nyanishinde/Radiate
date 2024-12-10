package com.example.radiate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radiate.databinding.OnboardingPagesBinding

class OnboardingAdapter(private val items:List<OnboardingItemsDC>):RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(private val binding: OnboardingPagesBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(itemsDC: OnboardingItemsDC){
            binding.imageViewOnboardings.setImageResource(itemsDC.imageResId)
            binding.textViewTitle.text = itemsDC.title
            binding.textViewDescription.text = itemsDC.description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingAdapter.OnboardingViewHolder {
        val binding = OnboardingPagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingAdapter.OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}