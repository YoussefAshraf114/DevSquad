package com.example.devsquad.presentation.viewmodels

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

class HomeViewModel(private val getCategoriesUseCase: GetCategoriesUseCase = GetCategoriesUseCase(RecipeRepoImpl())) : ViewModel() {

    private val _categoryList = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categoryList

    val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

//    val recipesByCategory: Map<String, List<Recipe>> by lazy {
//        listOf("chicken", "beef", "pork", "lamb", "fish")
//            .associateWith { getRecipesByCategory(it) }
//    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    init {
        getCategories()
        getRecipesByCategory("chicken")
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
            val getRecipesByCategoryUseCase = GetRecipesByCategoryUseCase(RecipeRepoImpl(), category)
            _isLoading.value = true
            try {
                _recipes.value = getRecipesByCategoryUseCase()
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}
