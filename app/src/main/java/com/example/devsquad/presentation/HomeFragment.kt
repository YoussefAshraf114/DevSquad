package com.example.devsquad.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.devsquad.MyApp
import com.example.devsquad.R
import com.example.devsquad.data.repo.RecipeRepoImpl
import com.example.devsquad.databinding.FragmentHomeBinding
import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.usecases.GetRecipeByIdUseCase
import com.example.devsquad.presentation.adapters.CategoryAdapter
import com.example.devsquad.presentation.adapters.RecipeAdapter
import com.example.devsquad.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    val categoryAdapter by lazy {
        CategoryAdapter()
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
            adapter = categoryAdapter.apply {
                viewModel.categories.observe(viewLifecycleOwner) {
                    setCategories(it)
                }

                onItemClick = {
                    when (it.categoryName) {
                        "All Recipes" -> viewModel.getRecipesByCategory("All Recipes")
                        else -> viewModel.getRecipesByCategory(it.categoryName)
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

                onItemClick = {
                    lifecycleScope.launch {
                        onRecipeClick(it)
                    }
                }
            }
        }

        refreshAllRecipes()

        binding.searchView.setOnClickListener{
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun refreshAllRecipes() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRecipesByCategory("All Recipes")
            categoryAdapter.notifyItemChanged(categoryAdapter.selectedPosition)
            categoryAdapter.selectedPosition = 0
            categoryAdapter.notifyItemChanged(0)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    fun onRecipeClick(recipe: Recipe) {
        val bundle = Bundle().apply {
            putString("id", recipe.idMeal)
        }
        val navController = findNavController()
        navController.navigate(R.id.recipeDetailsFragment, bundle)
        Log.d("HomeFragment", "Recipe clicked: ${recipe}")
        Log.d("HomeFragment", "Id clicked: ${recipe.idMeal}")
    }
}
