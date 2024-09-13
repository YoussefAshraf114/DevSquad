package com.example.devsquad.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devsquad.databinding.FragmentHomeBinding
import com.example.devsquad.presentation.adapters.CategoryAdapter
import com.example.devsquad.presentation.adapters.RecipeAdapter
import com.example.devsquad.presentation.viewmodels.HomeViewModel

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
        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoryAdapter().apply {
                viewModel.categories.observe(viewLifecycleOwner) {
                    setCategories(it)
                }
            }
        }

        binding.recipeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecipeAdapter().apply {
                viewModel.recipes.observe(viewLifecycleOwner) {
                    setRecipes(it)
                }
            }
        }
    }
}
