package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IngredientAdapter (private val ingredientList: List<Ingredient>):
        RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>(){

        class IngredientViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
            val iconImageView:ImageView=itemView.findViewById(R.id.ingredientIcon)
            val nameTextView:TextView=itemView.findViewById(R.id.ingredientName)
            val quantityTextView:TextView=itemView.findViewById(R.id.ingredientQuantity)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.ingrediant_item,parent,false)
            return IngredientViewHolder(view)
        }

        override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
            val ingredient=ingredientList[position]
            holder.iconImageView.setImageResource(ingredient.iconResId)
            holder.nameTextView.text=ingredient.name
            holder.quantityTextView.text =ingredient.quantity
        }

        override fun getItemCount(): Int {
            return ingredientList.size
        }
}