package com.pickyberry.shop.domain

import com.pickyberry.base.util.Resource
import com.pickyberry.shop.data.Item
import com.pickyberry.shop.data.ItemsList


interface ShopRepository {

    suspend fun getLatest(): Resource<ItemsList>

    suspend fun getSale(): Resource<ItemsList>

}