package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devsquad.R

class RecipeDetailsFragment : Fragment() {

    private lateinit var videoView: VideoView
    private lateinit var ingredientsRecyclerView: RecyclerView
    private lateinit var back_btn:ImageButton
    private lateinit var start_btn:ImageButton
    private lateinit var pause_btn:ImageButton
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private val ingredientsList = listOf(
        Ingredient("Sheep's butt", R.drawable.ing_icon,"500g"),
        Ingredient("Lamb",R.drawable.ing_icon,"500g"),
        Ingredient("Rice",R.drawable.ing_icon,"1000g"),
        Ingredient("Carrot",R.drawable.ing_icon,"400g"),
        Ingredient("Onion",R.drawable.ing_icon,"300g")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_details, container, false)

        videoView = view.findViewById(R.id.videoView)
        val videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.recipe_video)
        videoView.setVideoURI(videoUri)


        ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView)
        ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        ingredientsAdapter = IngredientsAdapter(ingredientsList)
        ingredientsRecyclerView.adapter = ingredientsAdapter
        back_btn.findViewById<ImageButton>(R.id.back_btn)
        back_btn.setOnClickListener {
            onDestroy()
        }
        start_btn.findViewById<ImageButton>(R.id.start_btn)
        pause_btn.findViewById<ImageButton>(R.id.pause_btn)
        start_btn.setOnClickListener {
            pause_btn.visibility=View.VISIBLE
            start_btn.visibility=View.INVISIBLE
            videoView.start()
        }
        pause_btn.setOnClickListener {
            start_btn.visibility=View.VISIBLE
            pause_btn.visibility=View.INVISIBLE
            videoView.pause()
        }



        return view
    }


}
