package com.pickyberry.shop.presentation.firstpage.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pickyberry.shop.ShopActivity
import com.pickyberry.shop.data.Item
import com.pickyberry.shop.databinding.ItemLatestBinding


class CarouselLatestAdapter(
    private val carouselDataList: List<Item>,
    private val activity: ShopActivity,
) :
    RecyclerView.Adapter<CarouselLatestAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(val binding: ItemLatestBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestBinding.inflate(inflater, parent, false)
        return CarouselItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val item = carouselDataList[position]
        val binding = holder.binding
        binding.category.text = item.category
        binding.name.text = item.name
        binding.price.text = "$" + item.price.toString()
        binding.image.scaleType = ImageView.ScaleType.CENTER_INSIDE

        Glide
            .with(activity)
            .load(item.image_url)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?,
                ) {
                    binding.image.background = resource
                }
            })


    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }


}