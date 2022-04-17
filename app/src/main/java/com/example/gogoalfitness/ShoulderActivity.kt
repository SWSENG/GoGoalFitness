package com.example.gogoalfitness

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.gogoalfitness.adapter.LegAdapter
import com.example.gogoalfitness.adapter.ShoulderAdapter
import com.example.gogoalfitness.databinding.ActivityShoulderBinding
import com.example.gogoalfitness.list.LegList
import com.example.gogoalfitness.list.ShoulderList
import com.google.firebase.database.*

class ShoulderActivity : AppCompatActivity() {

    private lateinit var binding :ActivityShoulderBinding
    private lateinit var shoulderArrayList: ArrayList<ShoulderList>
    private lateinit var database: DatabaseReference
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoulderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shoulderArrayList = arrayListOf()
        listView = findViewById(R.id.listview)
        database = FirebaseDatabase.getInstance().getReference("Workout").child("ShoulderWorkout")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (e in snapshot.children) {
                    val name = e.child("name").value.toString()
                    val subTitle = e.child("subTitle").value.toString()
                    val gifId = e.child("gifId").value.toString()
                    val desc = e.child("desc").value.toString()

                    shoulderArrayList.add(ShoulderList(name, subTitle, gifId, desc))

                    Log.d(ContentValues.TAG, snapshot.child("name").value.toString())
                    val adapter = ShoulderAdapter(this@ShoulderActivity, shoulderArrayList)
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


        binding.shoulderStartBtn.setOnClickListener{
            val intent = Intent(this, StartShoulderWorkout::class.java)
            startActivity(intent)
        }

        binding.listview.setOnItemClickListener{parent, view, position, id ->

            val title = shoulderArrayList[position].title
            val subTitle = shoulderArrayList[position].subTitle
            val gifId = shoulderArrayList[position].gifId
            val desc = shoulderArrayList[position].desc

            val i = Intent(this, ShoulderExerciseDescActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("gifId", gifId)
            i.putExtra("desc", desc)

            startActivity(i)
        }
    }
}