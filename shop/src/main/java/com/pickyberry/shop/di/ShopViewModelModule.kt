package com.pickyberry.shop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pickyberry.shop.presentation.firstpage.FirstPageViewmodel
import com.pickyberry.shop.presentation.firstpage.ShopViewmodelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass


@Module
abstract class ShopViewModelModule {

    @Binds
    @IntoMap
    @ShopScope
    @ShopViewModelKey(FirstPageViewmodel::class)
    abstract fun bindMyViewModel(view: FirstPageViewmodel): ViewModel


    @Binds
    @ShopScope
    abstract fun bindViewModelFactory(factory: ShopViewmodelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ShopViewModelKey(val value: KClass<out ViewModel>)