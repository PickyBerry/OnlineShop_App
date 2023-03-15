package com.pickyberry.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pickyberry.authorization.databinding.ActivityAuthBinding
import com.pickyberry.authorization.di.AuthComponent
import com.pickyberry.authorization.di.DaggerAuthComponent
import com.pickyberry.base.di.DaggerDbComponent
import com.pickyberry.base.di.DbComponent


class AuthActivity : AppCompatActivity() {

    //Setting up DI for authorization module
    lateinit var dbComponent: DbComponent
    lateinit var authComponent: AuthComponent
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        dbComponent = DaggerDbComponent.builder().application(application).build()
        authComponent = DaggerAuthComponent.builder().application(application).dbComponent(dbComponent).build()
        setContentView(binding.root)
    }


}