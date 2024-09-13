package com.example.devsquad.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devsquad.databinding.FragmentHomeBinding
import com.example.devsquad.presentation.adapters.CategoryAdapter
import com.example.devsquad.presentation.adapters.RecipeAdapter
import com.example.devsquad.presentation.viewmodels.HomeViewModel
import com.example.devsquad.R
import com.example.devsquad.domain.usecases.GetRecipesByCategoryUseCase
import com.example.devsquad.data.repo.RecipeRepoImpl
import com.example.devsquad.presentation.DetailsFragment

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeAdapter = RecipeAdapter()
        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter().apply {
                viewModel.categories.observe(viewLifecycleOwner) {
                    setCategories(it)
                }

                onItemClick = {
                    when (it.categoryName) {
                        "All Recipes" -> viewModel.getRecipesByCategory("All Recipes")
                        "Beef" -> viewModel.getRecipesByCategory("Beef")
                        "Breakfast" -> viewModel.getRecipesByCategory("Breakfast")
                        "Chicken" -> viewModel.getRecipesByCategory("Chicken")
                        "Dessert" -> viewModel.getRecipesByCategory("Dessert")
                        "Goat" -> viewModel.getRecipesByCategory("Goat")
                        "Lamb" -> viewModel.getRecipesByCategory("Lamb")
                        "Miscellaneous" -> viewModel.getRecipesByCategory("Miscellaneous")
                        "Pasta" -> viewModel.getRecipesByCategory("Pasta")
                        "Pork" -> viewModel.getRecipesByCategory("Pork")
                        "Seafood" -> viewModel.getRecipesByCategory("Seafood")
                        "Side" -> viewModel.getRecipesByCategory("Side")
                        "Starter" -> viewModel.getRecipesByCategory("Starter")
                        "Vegan" -> viewModel.getRecipesByCategory("Vegan")
                        "Vegetarian" -> viewModel.getRecipesByCategory("Vegetarian")
                        else -> viewModel.getRecipesByCategory("All Recipes")
                    }
                }
            }
        }

        binding.recipeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipeAdapter.apply {
                viewModel.recipes.observe(viewLifecycleOwner) {
                    setRecipes(it)
                }

//                onItemClick = {
//                    val recipeById = viewModel.getRecipeById(it.idMeal)
//                    if (recipeById != null) {
//                        val bundle = Bundle()
//                        bundle.putParcelable("recipe", recipeById.toRecipe())
//                        val detailsFragment = DetailsFragment()
//                        detailsFragment.arguments = bundle
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, detailsFragment)
//                            .addToBackStack(null)
//                    }
//                }
            }
        }
    }
}
