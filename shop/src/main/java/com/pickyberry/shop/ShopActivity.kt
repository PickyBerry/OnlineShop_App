package com.pickyberry.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.pickyberry.base.di.DaggerDbComponent
import com.pickyberry.base.di.DbComponent
import com.pickyberry.shop.databinding.ActivityShopBinding
import com.pickyberry.shop.di.DaggerShopComponent
import com.pickyberry.shop.di.ShopComponent

class ShopActivity : AppCompatActivity() {

    //Setting up DI for shop module
    lateinit var dbComponent: DbComponent
    lateinit var shopComponent: ShopComponent
    lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        dbComponent = DaggerDbComponent.builder().application(application).build()
        shopComponent =
            DaggerShopComponent.builder().application(application).dbComponent(dbComponent).build()
        setNavigation()
        setContentView(binding.root)
    }


    private fun setNavigation() {

        binding.profile.setOnClickListener {
            binding.home.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.profile.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    com.pickyberry.base.R.color.lightgray_background
                )
            )
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://pickyberry.testapp/profile_fragment".toUri())
                .build()
            findNavController(binding.fragmentContainerView.id).navigate(request)
        }

        binding.home.setOnClickListener {
            binding.profile.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.home.setCardBackgroundColor(
                ContextCompat.getColor(
                    this,
                    com.pickyberry.base.R.color.lightgray_background
                )
            )
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://pickyberry.testapp/firstpage_fragment".toUri())
                .build()
            findNavController(binding.fragmentContainerView.id).navigate(request)
        }
    }


}