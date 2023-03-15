package com.pickyberry.shop.presentation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pickyberry.shop.R
import com.pickyberry.shop.ShopActivity
import com.pickyberry.shop.databinding.FragmentProfileBinding
import java.io.IOException


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        setOnClickListeners()
        setUpBar()
        return binding.root
    }

    private fun setOnClickListeners() {

        //Navigating back
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        //Logging out to first screen
        binding.logout.setOnClickListener {
            val intent = Intent(
                activity,
                Class.forName("com.pickyberry.authorization.AuthActivity")
            ).apply {}
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        //Intent for changing profile pic
        binding.btnChangePhoto.setOnClickListener {
            startGalleryLauncher.launch(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
            )
        }
    }

    //Setting up bottom navigation bar
    private fun setUpBar() {
        (requireActivity() as ShopActivity).binding.profile.setCardBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                com.pickyberry.base.R.color.lightgray_background
            )
        )

        (requireActivity() as ShopActivity).binding.home.setCardBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.white
            )
        )
    }

    //Activity result launcher for changing profile pic
    private var startGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK)
            try {
                val savedBitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().applicationContext.contentResolver,
                    result.data!!.data
                )
                binding.profilePic.setImageBitmap(savedBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
    }

}