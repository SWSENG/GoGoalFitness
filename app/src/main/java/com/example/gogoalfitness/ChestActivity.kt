package com.example.gogoalfitness

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.gogoalfitness.adapter.ArmAdapter
import com.example.gogoalfitness.adapter.ChestAdapter
import com.example.gogoalfitness.databinding.ActivityChestBinding
import com.example.gogoalfitness.list.ArmList
import com.example.gogoalfitness.list.ChestList
import com.google.firebase.database.*

class ChestActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChestBinding
    private lateinit var chestArrayList: ArrayList<ChestList>
    private lateinit var database: DatabaseReference
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chestArrayList = arrayListOf()
        listView = findViewById(R.id.Chestlistview)
        database = FirebaseDatabase.getInstance().getReference("Workout").child("ChestWorkout")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (e in snapshot.children) {
                    val name = e.child("name").value.toString()
                    val subTitle = e.child("subTitle").value.toString()
                    val gifId = e.child("gifId").value.toString()
                    val desc = e.child("desc").value.toString()

                    chestArrayList.add(ChestList(name, subTitle, gifId, desc))
                    val adapter = ChestAdapter(this@ChestActivity, chestArrayList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    applicationContext, "error reading data",
                    Toast.LENGTH_LONG
                ).show();
            }

        })

        binding.chestStartBtn.setOnClickListener {
            val intent = Intent(this, StartChestWorkout::class.java)
            startActivity(intent)
        }

        binding.Chestlistview.setOnItemClickListener { parent, view, position, id ->

            val title = chestArrayList[position].title
            val subTitle = chestArrayList[position].subTitle
            val gifId = chestArrayList[position].gifId
            val desc = chestArrayList[position].desc

            val i = Intent(this, ChestExerciseDescActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("gifId", gifId)
            i.putExtra("desc", desc)

            startActivity(i)
        }
    }
}