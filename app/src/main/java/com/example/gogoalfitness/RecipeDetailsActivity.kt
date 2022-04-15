package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityRecipeDetailsBinding
import com.squareup.picasso.Picasso

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra("image").toString()
        val name = intent.getStringExtra("name").toString()
        val ingredients = intent.getStringExtra("ingredients").toString()
        val directions = intent.getStringExtra("directions").toString()

        Picasso.get().load(image).into(binding.ivRecipeMain)
        binding.recipeTitleMain.text = name
        binding.contentIngredients.text = ingredients
        binding.contentDirections.text = directions

    }
}