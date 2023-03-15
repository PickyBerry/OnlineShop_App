package com.pickyberry.shop.presentation.firstpage

import androidx.lifecycle.ViewModel
import com.pickyberry.base.data.User
import com.pickyberry.base.util.Resource
import com.pickyberry.shop.data.Item
import com.pickyberry.shop.data.ItemsList
import com.pickyberry.shop.domain.ShopRepository
import javax.inject.Inject

class FirstPageViewmodel @Inject constructor(
    private val shopRepository: ShopRepository
) : ViewModel() {

    suspend fun getLatest(): Resource<ItemsList> = shopRepository.getLatest()

    suspend fun getSale(): Resource<ItemsList> = shopRepository.getSale()
}