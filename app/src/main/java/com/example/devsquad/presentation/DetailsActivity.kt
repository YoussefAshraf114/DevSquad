package com.example.myapplication

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R.id.Sample_video

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val videoView:VideoView=findViewById(Sample_video)
        val videoPath="android.resource://"+packageName+"/"+R.raw.sample_video
        videoView.setVideoURI(Uri.parse(videoPath))
        videoView.start()
        val ingredientList= listOf(
            Ingredient(R.drawable.baseline_fastfood_24,"flour","2 cups"),
            Ingredient(R.drawable.baseline_fastfood_24,"sugar","1 cup"),
            Ingredient(R.drawable.baseline_fastfood_24,"Butter","100g")
        )

        var recyclerView:RecyclerView=findViewById(R.id.recycler_view_ingredients)
        recyclerView.layoutManager=LinearLayoutManager(this)
        var ingredientAdapter:IngredientAdapter=IngredientAdapter(ingredientList)
        recyclerView.adapter=ingredientAdapter

        val back_button:ImageView=findViewById(R.id.back_btn)
        back_button.setOnClickListener {
            onDestroy()
        }
    }
}