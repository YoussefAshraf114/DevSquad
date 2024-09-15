package com.example.devsquad.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.devsquad.R
import com.example.devsquad.domain.entity.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    var onItemClick: ((Recipe) -> Unit)? = null
    var onLikeClick: ((CheckBox) -> Unit)? = null

    val recipes: MutableList<Recipe> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(recipes[position])
        }
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recipes_anim))
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.name)
        val recipeImage = itemView.findViewById<ImageView>(R.id.recipe_image)
        fun bind(recipe: Recipe) {
            title.text = recipe.title
            Glide.with(itemView.context).load(recipe.image).into(recipeImage)
        }
    }

    fun setRecipes(data: List<Recipe>) {
        recipes.clear()
        recipes.addAll(data)
        notifyDataSetChanged()
    }

}