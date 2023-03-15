package com.pickyberry.shop.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.pickyberry.base.di.DbComponent
import com.pickyberry.base.network.RequestInterceptor
import com.pickyberry.shop.data.API
import com.pickyberry.shop.data.ShopRepositoryImpl
import com.pickyberry.shop.domain.ShopRepository
import com.pickyberry.shop.presentation.firstpage.FirstPageScreen
import com.pickyberry.shop.presentation.firstpage.FirstPageViewmodel
import com.pickyberry.shop.presentation.firstpage.ShopViewmodelFactory
import dagger.*
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ShopScope


@ShopScope
@Component(
    modules = [ShopModule::class, ShopViewModelModule::class],
    dependencies = [DbComponent::class]
)
interface ShopComponent {

    fun inject(fragment: FirstPageScreen)
    fun inject(viewmodel: FirstPageViewmodel)
    fun inject(factory: ShopViewmodelFactory)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder


        fun dbComponent(dbComponent: DbComponent): Builder

        fun build(): ShopComponent

    }

}
