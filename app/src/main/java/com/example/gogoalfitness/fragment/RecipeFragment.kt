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
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.FragmentRecipeBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.database.*


class RecipeFragment : Fragment(), RecipeAdapter.OnItemClickListener{

    private lateinit var binding: FragmentRecipeBinding
    private lateinit var database: DatabaseReference

    private var immuneArrayList:ArrayList<RecipeInfo> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        getRecipeData("Immune")


        val immuneAdapter = RecipeAdapter(immuneArrayList,this)
        binding.recyclerViewImmuneSupport.adapter = immuneAdapter
        binding.recyclerViewImmuneSupport.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerViewImmuneSupport.setHasFixedSize(true)


        return binding.root
    }

    private fun getRecipeData(path:String) {

        //immuneArrayList.clear()
        database = FirebaseDatabase.getInstance().getReference(path)

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    for (recipeSnapshot in snapshot.children){
                        val name = recipeSnapshot.child("name").value.toString()
                        val image = recipeSnapshot.child("image").value.toString()
                        val calories = recipeSnapshot.child("calories").value.toString()
                        val ingredients = recipeSnapshot.child("ingredients").value.toString()
                        val directions = recipeSnapshot.child("directions").value.toString()
                        immuneArrayList.add(RecipeInfo(name, image, calories, ingredients, directions))

                    }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(context, "error reading data",
                    Toast.LENGTH_LONG).show();
            }

        })


    }

    override fun itemClick(position: Int) {
        val intent = Intent(context, RecipeDetailsActivity::class.java)

        intent.putExtra("image", immuneArrayList[position].image)
        intent.putExtra("name", immuneArrayList[position].name)
        intent.putExtra("ingredients", immuneArrayList[position].ingredients)
        intent.putExtra("directions", immuneArrayList[position].directions)
        startActivity(intent)
    }


}