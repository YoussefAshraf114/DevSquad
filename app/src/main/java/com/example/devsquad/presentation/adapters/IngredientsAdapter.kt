package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientsAdapter(private val ingredients: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientName: TextView = itemView.findViewById(R.id.ing_name)
        private val ingredientIcon:ImageView=itemView.findViewById(R.id.ing_icon)
        private val ingredientQuantity:TextView=itemView.findViewById(R.id.ing_quantity)

        fun bind(ingredient: Ingredient) {
            ingredientName.text = ingredient.name
            ingredientIcon.setImageResource(ingredient.icon)
            ingredientQuantity.text=ingredient.quantity
        }
    }
}
