package com.pickyberry.base.di

import android.app.Application
import androidx.room.Room
import com.pickyberry.base.data.UserDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [DbModule::class])
interface DbComponent{

     fun userDb():UserDatabase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DbComponent

    }

}



