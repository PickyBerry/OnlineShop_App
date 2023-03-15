package com.pickyberry.base.di

import android.app.Application
import androidx.room.Room
import com.pickyberry.base.data.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule {

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "userdb.db"
        ).build()
    }

}