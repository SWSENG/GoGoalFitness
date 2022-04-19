package com.example.gogoalfitness

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.ActivityBookmarkBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class BookmarkActivity : AppCompatActivity(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: ActivityBookmarkBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarBookmark)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid)
        }
        val recyclerViewBookmark = binding.recyclerViewBookmark
        val bookmarkArrayList = arrayListOf<RecipeInfo>()

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

                        val adapter = RecipeAdapter(bookmarkArrayList, this@BookmarkActivity)
                        recyclerViewBookmark.adapter = adapter
                        recyclerViewBookmark.layoutManager =
                            GridLayoutManager(applicationContext, 2)
                        recyclerViewBookmark.setHasFixedSize(true)
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