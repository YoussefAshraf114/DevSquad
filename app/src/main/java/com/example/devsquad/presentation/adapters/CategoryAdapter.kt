package com.example.devsquad.presentation.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.devsquad.R
import com.example.devsquad.domain.entity.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    val data: MutableList<Category> = mutableListOf()

    var onItemClick: ((Category) -> Unit)? = null
    var selectedPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        return CategoryViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int,
    ) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(data[position])
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }
        if (position == selectedPosition) {
            holder.title.setTextColor(Color.WHITE)
            holder.card.setCardBackgroundColor(Color.GREEN)

        } else {
            holder.title.setTextColor(Color.BLACK)
            holder.card.setCardBackgroundColor(Color.WHITE)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.category_name)
        val card = itemView.findViewById<CardView>(R.id.category_card_view)
        fun bind(category: Category) {
            title.text = category.categoryName
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: List<Category>) {
        data.clear()
        data.add(Category("0", "All Recipes", "", ""))
        data.addAll(categories)
        notifyDataSetChanged()
    }
}