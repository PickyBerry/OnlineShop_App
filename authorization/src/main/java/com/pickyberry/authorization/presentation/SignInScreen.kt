package com.pickyberry.authorization.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import com.pickyberry.authorization.AuthActivity
import com.pickyberry.authorization.databinding.FragmentSignInBinding
import com.pickyberry.authorization.util.EmailValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class SignInScreen : Fragment() {

    private lateinit var binding: FragmentSignInBinding


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: AuthorizationViewmodel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthorizationViewmodel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        (activity as AuthActivity).authComponent.inject(this)
        setOnClickListeners()
        return binding.root

    }

    private fun setOnClickListeners(){
        binding.btnSignIn.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()

            if (!EmailValidator.isValid(email))
                Toast.makeText(
                    requireContext(),
                    "Invalid e-mail!",
                    Toast.LENGTH_LONG
                ).show()
            else if (binding.etFirstName.text.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Error: Empty Username!",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                runBlocking {

                    if (!viewModel.getByMail(email)) {
                        viewModel.addUser(
                            email,
                            firstName,
                            lastName
                        )

                        startActivity(Intent(
                            activity,
                            Class.forName("com.pickyberry.shop.ShopActivity")
                        ).apply {
                        })

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "User with such email already exists!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }

        binding.btnLogin.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://pickyberry.testapp/login_fragment".toUri())
                .build()
            it.findNavController()
                .navigate(request)
        }

        binding.google.setOnClickListener {
            startActivity(Intent(
                activity,
                Class.forName("com.pickyberry.shop.ShopActivity")
            ).apply {
            })
        }

        binding.apple.setOnClickListener {
            startActivity(Intent(
                activity,
                Class.forName("com.pickyberry.shop.ShopActivity")
            ).apply {
            })
        }
    }

}