package com.pickyberry.authorization.di


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pickyberry.authorization.data.AuthRepositoryImpl
import com.pickyberry.authorization.domain.AuthRepository
import com.pickyberry.authorization.presentation.AuthorizationViewmodel
import com.pickyberry.authorization.presentation.SignInScreen
import com.pickyberry.authorization.presentation.AuthViewModelFactory
import com.pickyberry.base.data.UserDatabase
import com.pickyberry.base.di.DbComponent

import kotlin.reflect.KClass

import dagger.*
import dagger.multibindings.IntoMap
import layout.LogInScreen
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthScope


@AuthScope
@Component(
    modules = [AuthModule::class, ViewModelModule::class],
   dependencies = [DbComponent::class]
)
interface AuthComponent {

    fun inject(fragment: SignInScreen)
    fun inject(fragment: LogInScreen)
    fun inject(viewmodel: AuthorizationViewmodel)
    fun inject(factory: AuthViewModelFactory)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder


        fun dbComponent(dbComponent: DbComponent): Builder

        fun build(): AuthComponent

    }

}

@Module
class AuthModule {

    @Provides
    fun provideRepository(db: UserDatabase): AuthRepository = AuthRepositoryImpl(db)

}


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewmodel::class)
    abstract fun bindMyViewModel(view: AuthorizationViewmodel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)



