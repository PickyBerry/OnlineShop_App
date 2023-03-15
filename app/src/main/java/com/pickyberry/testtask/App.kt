package com.pickyberry.testtask

import android.app.Application
import com.pickyberry.authorization.di.AuthComponent
import com.pickyberry.base.di.DaggerDbComponent
import com.pickyberry.base.di.DbComponent
import com.pickyberry.authorization.di.DaggerAuthComponent
import com.pickyberry.shop.di.DaggerShopComponent
import com.pickyberry.shop.di.ShopComponent

class App: Application() {

    //Setting up DI components for the project
    companion object{
    lateinit var dbComponent: DbComponent
    lateinit var authComponent: AuthComponent
    lateinit var shopComponent: ShopComponent
    }


    override fun onCreate() {
        super.onCreate()

        dbComponent= DaggerDbComponent.builder()
            .application(this)
            .build()

        authComponent= DaggerAuthComponent.builder().application(this@App).dbComponent(dbComponent).build()

        shopComponent= DaggerShopComponent.builder().application(this@App).dbComponent(dbComponent).build()

    }


}