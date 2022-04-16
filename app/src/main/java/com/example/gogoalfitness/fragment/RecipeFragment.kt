package com.example.gogoalfitness.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gogoalfitness.BookmarkActivity
import com.example.gogoalfitness.R
import com.example.gogoalfitness.RecipeDetailsActivity
import com.example.gogoalfitness.RecipeViewMoreActivity
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.FragmentRecipeBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class RecipeFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: FragmentRecipeBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseUser: DatabaseReference
    private lateinit var databaseStorage: DatabaseReference

    private var bookmarkArrayList = arrayListOf<RecipeInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.let {
            val uid = user.uid
            databaseUser = FirebaseDatabase.getInstance().getReference(uid)
        }

        databaseUser.child("Bookmarked")
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

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        context, "error reading data",
                        Toast.LENGTH_LONG
                    ).show();
                }
            })


        val recyclerViewImmune = binding.recyclerViewImmuneSupport
        getRecipeData(recyclerViewImmune, "Immune")

        val recyclerViewBreakfast = binding.recyclerViewBreakfast
        getRecipeData(recyclerViewBreakfast, "Breakfast")

        val recyclerViewPreWorkout = binding.recyclerViewPreworkout
        getRecipeData(recyclerViewPreWorkout, "PreWorkout")

        val recyclerViewPostWorkout = binding.recyclerViewPostworkout
        getRecipeData(recyclerViewPostWorkout, "PostWorkout")

        binding.viewMoreTextImmuneSupport.setOnClickListener() {
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Immune Support")
            intent.putExtra("path", "Immune")
            startActivity(intent)
        }

        binding.viewMoreTextBreakfast.setOnClickListener() {
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Breakfast")
            intent.putExtra("path", "Breakfast")
            startActivity(intent)
        }

        binding.viewMoreTextPreworkout.setOnClickListener() {
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Pre-Workout")
            intent.putExtra("path", "PreWorkout")
            startActivity(intent)
        }

        binding.viewMoreTextPostworkout.setOnClickListener() {
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Post-Workout")
            intent.putExtra("path", "PostWorkout")
            startActivity(intent)
        }


        var toolbar: Toolbar = binding.toolbarRecipe
        toolbar.inflateMenu(R.menu.recipe_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menuButtonBookmark -> {
                    val intent = Intent(context, BookmarkActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        return binding.root
    }

    private fun getRecipeData(recyclerView: RecyclerView, path: String) {

        val tempArrayList = arrayListOf<RecipeInfo>()

        databaseStorage = FirebaseDatabase.getInstance().getReference(path)
        databaseStorage.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (recipeSnapshot in snapshot.children) {
                    val name = recipeSnapshot.child("name").value.toString()
                    val image = recipeSnapshot.child("image").value.toString()
                    val calories = recipeSnapshot.child("calories").value.toString()
                    val ingredients = recipeSnapshot.child("ingredients").value.toString()
                    val directions = recipeSnapshot.child("directions").value.toString()

                    tempArrayList.add(RecipeInfo(name, image, calories, ingredients, directions))
                    val adapter = RecipeAdapter(tempArrayList, this@RecipeFragment)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.setHasFixedSize(true)
                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(
                    context, "error reading data",
                    Toast.LENGTH_LONG
                ).show();
            }

        })

    }

    override fun itemClick(arrayList: ArrayList<RecipeInfo>, position: Int) {
        val intent = Intent(context, RecipeDetailsActivity::class.java)

        intent.putExtra("image", arrayList[position].image)
        intent.putExtra("name", arrayList[position].name)
        intent.putExtra("calories", arrayList[position].calories)
        intent.putExtra("ingredients", arrayList[position].ingredients)
        intent.putExtra("directions", arrayList[position].directions)
        startActivity(intent)
    }


}