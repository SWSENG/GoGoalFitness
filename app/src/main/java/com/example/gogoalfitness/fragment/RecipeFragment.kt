package com.example.gogoalfitness.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.RecipeDetailsActivity
import com.example.gogoalfitness.RecipeViewMoreActivity
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.FragmentRecipeBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.database.*


class RecipeFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var database: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater)

        val recyclerViewImmune = binding.recyclerViewImmuneSupport
        getRecipeData(recyclerViewImmune,"Immune")

        val recyclerViewBreakfast = binding.recyclerViewBreakfast
        getRecipeData(recyclerViewBreakfast,"Breakfast")

        val recyclerViewPreWorkout = binding.recyclerViewPreworkout
        getRecipeData(recyclerViewPreWorkout,"PreWorkout")

        val recyclerViewPostWorkout = binding.recyclerViewPostworkout
        getRecipeData(recyclerViewPostWorkout,"PostWorkout")

        binding.viewMoreTextImmuneSupport.setOnClickListener(){
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Immune Support")
            intent.putExtra("path", "Immune")
            startActivity(intent)
        }

        binding.viewMoreTextBreakfast.setOnClickListener(){
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Breakfast")
            intent.putExtra("path", "Breakfast")
            startActivity(intent)
        }

        binding.viewMoreTextPreworkout.setOnClickListener(){
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Pre-Workout")
            intent.putExtra("path", "PreWorkout")
            startActivity(intent)
        }

        binding.viewMoreTextPostworkout.setOnClickListener(){
            val intent = Intent(context, RecipeViewMoreActivity::class.java)
            intent.putExtra("title", "Post-Workout")
            intent.putExtra("path", "PostWorkout")
            startActivity(intent)
        }

        return binding.root
    }

    private fun getRecipeData(recyclerView: RecyclerView,path: String) {

        val tempArrayList = arrayListOf<RecipeInfo>()

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

                    val adapter = RecipeAdapter(tempArrayList,this@RecipeFragment)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
        intent.putExtra("ingredients", arrayList[position].ingredients)
        intent.putExtra("directions", arrayList[position].directions)
        startActivity(intent)
    }


}