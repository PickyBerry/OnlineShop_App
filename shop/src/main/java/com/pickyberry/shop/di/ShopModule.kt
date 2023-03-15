package com.pickyberry.shop.di

import com.google.gson.GsonBuilder
import com.pickyberry.base.network.RequestInterceptor
import com.pickyberry.shop.data.API
import com.pickyberry.shop.data.ShopRepositoryImpl
import com.pickyberry.shop.domain.ShopRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ShopModule {

    @Provides
    @ShopScope
    fun provideRepository(api: API): ShopRepository = ShopRepositoryImpl(api)

    @Provides
    @ShopScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(RequestInterceptor)
                    .build()
            )
            .build()
    }

    @Provides
    @ShopScope
    fun provideListAPI(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }


    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/"
    }

}