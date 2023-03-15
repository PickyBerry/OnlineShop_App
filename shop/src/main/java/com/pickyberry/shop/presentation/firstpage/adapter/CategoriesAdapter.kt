package com.pickyberry.shop.presentation.firstpage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pickyberry.shop.data.Category
import com.pickyberry.shop.databinding.ItemCategoryBinding

class CategoriesAdapter(val categoriesList: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesItemViewHolder>() {

    class CategoriesItemViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoriesItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesItemViewHolder, position: Int) {
        val item = categoriesList[position]
        val binding = holder.binding
        binding.categoryName.text = item.name
        binding.categoryIcon.setBackgroundResource(item.icon)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }


}