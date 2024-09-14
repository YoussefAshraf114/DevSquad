package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devsquad.R
import com.example.devsquad.domain.entity.Recipe
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.DeclaredMemberIndex.Empty

class RecipeDetailsFragment : Fragment() {

    private lateinit var ingredientsRecyclerView: RecyclerView
    private lateinit var ingredientsAdapter: IngredientsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = arguments?.getSerializable("recipe") as Recipe


        val unFilteredList = listOf(
            Ingredient(recipe.ingredient1, R.drawable.ing_icon, recipe.measure1),
            Ingredient(recipe.ingredient2, R.drawable.ing_icon, recipe.measure2),
            Ingredient(recipe.ingredient3, R.drawable.ing_icon, recipe.measure3),
            Ingredient(recipe.ingredient4, R.drawable.ing_icon, recipe.measure4),
            Ingredient(recipe.ingredient5, R.drawable.ing_icon, recipe.measure5),
            Ingredient(recipe.ingredient6, R.drawable.ing_icon, recipe.measure6),
            Ingredient(recipe.ingredient7, R.drawable.ing_icon, recipe.measure7),
            Ingredient(recipe.ingredient8, R.drawable.ing_icon, recipe.measure8),
            Ingredient(recipe.ingredient9, R.drawable.ing_icon, recipe.measure9),
            Ingredient(recipe.ingredient10, R.drawable.ing_icon, recipe.measure10),
            Ingredient(recipe.ingredient11, R.drawable.ing_icon, recipe.measure11),
            Ingredient(recipe.ingredient12, R.drawable.ing_icon, recipe.measure12),
            Ingredient(recipe.ingredient13, R.drawable.ing_icon, recipe.measure13),
            Ingredient(recipe.ingredient14, R.drawable.ing_icon, recipe.measure14),
            Ingredient(recipe.ingredient15, R.drawable.ing_icon, recipe.measure15),
            Ingredient(recipe.ingredient16, R.drawable.ing_icon, recipe.measure16),
            Ingredient(recipe.ingredient17, R.drawable.ing_icon, recipe.measure17),
            Ingredient(recipe.ingredient18, R.drawable.ing_icon, recipe.measure18),
            Ingredient(recipe.ingredient19, R.drawable.ing_icon, recipe.measure19),
            Ingredient(recipe.ingredient20, R.drawable.ing_icon, recipe.measure20),
            )

        val filteredList=unFilteredList.filter {
            it.name!="" && it.quantity!="" && it.name!=null && it.quantity!=null
        }
        val ingredientsList =filteredList

            ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView)
        ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        ingredientsAdapter = IngredientsAdapter(ingredientsList)
        ingredientsRecyclerView.adapter = ingredientsAdapter


        Log.d("recipeDetailsFragment", "Recipe received: ${recipe}")
    }


}
