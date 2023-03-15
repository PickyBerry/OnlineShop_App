package com.pickyberry.shop.data

import android.util.Log
import com.pickyberry.base.util.Resource
import com.pickyberry.shop.domain.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(private val retrofitService: API) : ShopRepository {

    override suspend fun getLatest(): Resource<ItemsList> =
        handleResponse(retrofitService.getLatest())

    override suspend fun getSale(): Resource<ItemsList> =
        handleResponse(retrofitService.getSale())


    private fun handleResponse(response: retrofit2.Response<ItemsList>): Resource<ItemsList> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}