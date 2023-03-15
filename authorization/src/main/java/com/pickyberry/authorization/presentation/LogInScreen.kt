package layout


import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pickyberry.authorization.AuthActivity
import com.pickyberry.authorization.R
import com.pickyberry.authorization.databinding.FragmentLogInBinding
import com.pickyberry.authorization.presentation.AuthorizationViewmodel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class LogInScreen : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: AuthorizationViewmodel by lazy {
        ViewModelProvider(this, viewModelFactory)[AuthorizationViewmodel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater, container, false)
        (activity as AuthActivity).authComponent.inject(this)
        setOnClickListeners()
        binding.password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        return binding.root
    }



    private fun setOnClickListeners() {

        //Authorization listener. Checking for existing user
        binding.btnSignIn.setOnClickListener {
            runBlocking {
                val firstName = binding.etFirstName.text.toString()
                if (viewModel.getByName(firstName) == null) {
                    Toast.makeText(
                        requireContext(),
                        "Couldn't authorize with this information!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    startActivity(
                        Intent(
                            activity,
                            Class.forName("com.pickyberry.shop.ShopActivity")
                        ).apply {
                        })
                }
            }
        }

        //Password toggle visibility listener
        binding.visibility.setOnClickListener {
            if (binding.password.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                binding.password.inputType = InputType.TYPE_CLASS_TEXT
                binding.visibility.setImageResource(R.drawable.baseline_visibility_24)
            } else {
                binding.password.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.visibility.setImageResource(R.drawable.baseline_visibility_off_24)
            }
            binding.password.setSelection(binding.password.text.length)
        }
    }
}