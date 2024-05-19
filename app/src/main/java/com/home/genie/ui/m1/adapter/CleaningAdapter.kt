package com.home.genie.ui.m1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.home.genie.databinding.LayoutBeautyBinding
import com.home.genie.databinding.LayoutCleaningBinding

class CleaningAdapter(val data: List<BeautyModel>) :
    RecyclerView.Adapter<CleaningAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutCleaningBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: LayoutCleaningBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BeautyModel) {
            binding.productName.text = item.name
            binding.productImage.setImageResource(item.image)

        }
    }
}

data class BeautyModel(val name: String, val image: Int)