package com.example.gogoalfitness

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.ActivityRecipeViewMoreBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.database.*

class RecipeViewMoreActivity : AppCompatActivity(), RecipeAdapter.OnItemClickListener {
    private lateinit var binding: ActivityRecipeViewMoreBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeViewMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarRecipeViewMore)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarRecipeViewMore.title = intent.getStringExtra("title").toString()
        val path = intent.getStringExtra("path").toString()

        val tempArrayList = arrayListOf<RecipeInfo>()

        val adapter = RecipeAdapter(tempArrayList, this)

        database = FirebaseDatabase.getInstance().getReference(path)
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (recipeSnapshot in snapshot.children) {
                    val name = recipeSnapshot.child("name").value.toString()
                    val image = recipeSnapshot.child("image").value.toString()
                    val calories = recipeSnapshot.child("calories").value.toString()
                    val ingredients = recipeSnapshot.child("ingredients").value.toString()
                    val directions = recipeSnapshot.child("directions").value.toString()
                    tempArrayList.add(RecipeInfo(name, image, calories, ingredients, directions))

                    binding.recyclerViewViewMore.adapter = adapter
                    binding.recyclerViewViewMore.layoutManager =
                        GridLayoutManager(applicationContext, 2)
                    binding.recyclerViewViewMore.setHasFixedSize(true)
                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(
                    applicationContext, "error reading data",
                    Toast.LENGTH_LONG
                ).show();
            }

        })

    }

    override fun itemClick(arrayList: ArrayList<RecipeInfo>, position: Int) {
        val intent = Intent(applicationContext, RecipeDetailsActivity::class.java)
        intent.putExtra("image", arrayList[position].image)
        intent.putExtra("name", arrayList[position].name)
        intent.putExtra("ingredients", arrayList[position].ingredients)
        intent.putExtra("directions", arrayList[position].directions)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.home -> finish()
        }
        return true
    }
}