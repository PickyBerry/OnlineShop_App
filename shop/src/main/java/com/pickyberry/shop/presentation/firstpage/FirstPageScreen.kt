package com.pickyberry.shop.presentation.firstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pickyberry.base.util.Resource
import com.pickyberry.shop.R
import com.pickyberry.shop.ShopActivity
import com.pickyberry.shop.data.Category
import com.pickyberry.shop.data.ItemsList
import com.pickyberry.shop.databinding.FragmentFirstPageScreenBinding
import com.pickyberry.shop.presentation.firstpage.adapter.CarouselLatestAdapter
import com.pickyberry.shop.presentation.firstpage.adapter.CarouselSaleAdapter
import com.pickyberry.shop.presentation.firstpage.adapter.CategoriesAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


class FirstPageScreen : Fragment() {

    private lateinit var binding: FragmentFirstPageScreenBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel: FirstPageViewmodel by lazy {
        ViewModelProvider(this, viewModelFactory)[FirstPageViewmodel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFirstPageScreenBinding.inflate(layoutInflater, container, false)
        (activity as ShopActivity).shopComponent.inject(this)
        (activity as ShopActivity).setSupportActionBar(binding.toolbar)
        setCategories()
        setRecyclerviewsData()
        setUpBar()
        return binding.root
    }

    //Setting up bottom navigation bar
    private fun setUpBar() {
        (requireActivity() as ShopActivity).binding.profile.setCardBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                R.color.white
            )
        )

        (requireActivity() as ShopActivity).binding.home.setCardBackgroundColor(
            ContextCompat.getColor(
                requireActivity(),
                com.pickyberry.base.R.color.lightgray_background
            )
        )
    }

    //Categories carousel data
    private fun setCategories() {
        binding.categories.adapter =
            CategoriesAdapter(
                listOf(
                    Category("Phones", R.drawable.phones),
                    Category("Headphones", R.drawable.headphones),
                    Category("Games", R.drawable.games),
                    Category("Cars", R.drawable.cars),
                    Category("Furniture", R.drawable.furniture),
                    Category("Kids", R.drawable.kids)
                )
            )

    }

    //Fetching data for Latest and Flash Sale recyclerviews
    private fun setRecyclerviewsData() {

        //Data will be displayed only after both data sets were loaded
        lifecycleScope.launch {
            val latestData = fetchFromViewModel(true)
            val saleData = fetchFromViewModel(false)

            if (latestData?.latest != null) binding.latest.adapter =
                CarouselLatestAdapter(latestData.latest, activity as ShopActivity)

            if (saleData?.sale != null) binding.sale.adapter =
                CarouselSaleAdapter(saleData.sale, activity as ShopActivity)
        }

    }

    //Handling the response from viewmodel
    private suspend fun fetchFromViewModel(latestOrSale: Boolean): ItemsList? {

        var result: ItemsList? = null
        val resource: Resource<ItemsList>
        resource = if (latestOrSale) viewModel.getLatest()
        else viewModel.getSale()

        when (resource) {
            is Resource.Success -> {
                result = resource.data
            }

            is Resource.Error -> {
                resource.message?.let { message ->
                    Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
                }
            }

            is Resource.Loading -> {
                //   show scrollbar or placeholder
            }
        }
        return result
    }

}