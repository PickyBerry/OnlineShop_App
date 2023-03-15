package com.pickyberry.shop.data

import com.google.gson.annotations.SerializedName

data class SaleList(
    @SerializedName("flash_sale")
    val items: List<Item>
)
