package com.example.gogoalfitness.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.adapter.RecipeAdapter
import com.example.gogoalfitness.databinding.FragmentRecipeBinding
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.database.*


class RecipeFragment : Fragment(), RecipeAdapter.OnItemClickListener{


    private lateinit var binding: FragmentRecipeBinding
    private lateinit var database: DatabaseReference

    private lateinit var immuneRecyclerView: RecyclerView
    private lateinit var immuneArrayList : ArrayList<RecipeInfo>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)


        immuneRecyclerView = binding.recyclerViewImmuneSupport
        immuneRecyclerView.layoutManager = LinearLayoutManager(context)
        immuneRecyclerView.setHasFixedSize(true)

        immuneArrayList = arrayListOf<RecipeInfo>()
        getImmuneRecipeData()


        return view
    }

    private fun getImmuneRecipeData() {
        database = FirebaseDatabase.getInstance().getReference("Immune")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (recipeSnapshot in snapshot.children){
                        val recipe = recipeSnapshot.getValue(RecipeInfo::class.java)
                        immuneArrayList.add(recipe!!)
                        Toast.makeText(context, "reading data in children",
                            Toast.LENGTH_LONG).show();
                    }
                    immuneRecyclerView.adapter = RecipeAdapter(immuneArrayList,this@RecipeFragment)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun itemClick(position: Int) {
        TODO("Not yet implemented")
    }


}