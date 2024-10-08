package com.example.devsquad.presentation.viewmodels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.data.repo.RecipeRepoImpl
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.usecases.GetCategoriesUseCase
import com.example.devsquad.domain.usecases.GetRecipesByCategoryUseCase
import kotlinx.coroutines.launch
import com.example.devsquad.presentation.adapters.CategoryAdapter

class HomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase = GetCategoriesUseCase(
        RecipeRepoImpl()
    ),
) : ViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categoryList

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> = _searchResults

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    val categoryAdapter by lazy {
        CategoryAdapter()
    }
    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _categoryList.value = getCategoriesUseCase()
            } catch (e: Exception) {
                _error.value = e
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getRecipesByCategory(category: String) {
        viewModelScope.launch {
            if (category == "All Recipes") {
                val allRecipes: MutableList<Recipe> = mutableListOf()
                for (item in _categoryList.value!!) {
                    val getRecipes =
                        GetRecipesByCategoryUseCase(RecipeRepoImpl(), item.categoryName)
                    allRecipes.addAll(getRecipes())
                }
                _recipes.value = allRecipes
                _isLoading.value = true
                return@launch
            } else {
                _isLoading.value = true
                try {
                    val getRecipesByCategoryUseCase =
                        GetRecipesByCategoryUseCase(RecipeRepoImpl(), category)
                    _recipes.value = getRecipesByCategoryUseCase()
                } catch (e: Exception) {
                    _error.value = e
                }
            }
        }
    }

    fun getRecipeById(id: String): Recipe? {
        return _recipes.value?.find { it.idMeal == id }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            val filteredRecipes = recipes.value?.filter { recipe ->
                recipe.title.contains(query, ignoreCase = true)
            }
            _searchResults.value = filteredRecipes!!
        }
    }



}
