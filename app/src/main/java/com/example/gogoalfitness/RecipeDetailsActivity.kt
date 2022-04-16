package com.example.gogoalfitness

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.ActivityRecipeDetailsBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.*
import org.w3c.dom.Text
import java.util.regex.Pattern

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailsBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private var bookmarkArrayList = arrayListOf<RecipeInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid)
        }


        val pullImage = intent.getStringExtra("image").toString()
        val pullName = intent.getStringExtra("name").toString()
        val pullCalories = intent.getStringExtra("calories").toString()
        val pullIngredients = intent.getStringExtra("ingredients").toString()
        val pullDirections = intent.getStringExtra("directions").toString()

        Picasso.get().load(pullImage).into(binding.ivRecipeMain)
        binding.recipeTitleMain.text = pullName
        binding.contentIngredients.text = pullIngredients
        binding.contentIngredients.text =
            (binding.contentIngredients.text as String).replace("\\n", "\n")
        binding.contentDirections.text = pullDirections
        binding.contentDirections.text =
            (binding.contentDirections.text as String).replace("\\n", "\n")


        var bookmarked: Boolean = false

        database.child("Bookmarked")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (recipeSnapshot in snapshot.children) {
                        val name = recipeSnapshot.child("name").value.toString()
                        val image = recipeSnapshot.child("image").value.toString()
                        val calories = recipeSnapshot.child("calories").value.toString()
                        val ingredients = recipeSnapshot.child("ingredients").value.toString()
                        val directions = recipeSnapshot.child("directions").value.toString()
                        bookmarkArrayList.add(
                            RecipeInfo(
                                name,
                                image,
                                calories,
                                ingredients,
                                directions
                            )
                        )

                        if (pullName == name) {
                            bookmarked = true
                            binding.logoBookmarkDetails.visibility = View.VISIBLE
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        applicationContext, "error reading data",
                        Toast.LENGTH_LONG
                    ).show();
                }
            })

        binding.btnBookmarkDetails.setOnClickListener() {
            if (!bookmarked) {
                bookmarked = true
                binding.logoBookmarkDetails.visibility = View.VISIBLE

                //ADD INTO DATABASE
                database.child("Bookmarked")
                    .child(pullName).setValue(
                        RecipeInfo(
                            pullName,
                            pullImage,
                            pullCalories,
                            pullIngredients,
                            pullDirections
                        )
                    )

            } else if (bookmarked) {
                bookmarked = false
                binding.logoBookmarkDetails.visibility = View.INVISIBLE

                //DELETE FROM DATABASE
                database.child("Bookmarked")
                    .child(pullName).removeValue()


            }

        }


    }
}