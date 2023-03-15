package com.pickyberry.shop.data

import com.google.gson.annotations.SerializedName

data class ItemsList(
    @SerializedName("latest")
    val latest: List<Item>,

    @SerializedName("flash_sale")
    val sale: List<Item>
)
