package com.example.gogoalfitness

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.gogoalfitness.adapter.ArmAdapter
import com.example.gogoalfitness.adapter.LegAdapter
import com.example.gogoalfitness.databinding.ActivityLegBinding
import com.example.gogoalfitness.list.ArmList
import com.example.gogoalfitness.list.LegList
import com.google.firebase.database.*

class LegActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLegBinding
    private lateinit var legArrayList: ArrayList<LegList>
    private lateinit var database: DatabaseReference
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        legArrayList = arrayListOf()
        listView = findViewById(R.id.Leglistview)
        database = FirebaseDatabase.getInstance().getReference("Workout").child("LegWorkout")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (e in snapshot.children) {
                    val name = e.child("name").value.toString()
                    val subTitle = e.child("subTitle").value.toString()
                    val gifId = e.child("gifId").value.toString()
                    val desc = e.child("desc").value.toString()

                    legArrayList.add(LegList(name, subTitle, gifId, desc))

                    Log.d(ContentValues.TAG, snapshot.child("name").value.toString())
                    val adapter = LegAdapter(this@LegActivity, legArrayList)
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

        binding.legStartBtn.setOnClickListener{
            val intent = Intent(this, StartLegWorkout::class.java)
            startActivity(intent)
        }

        binding.Leglistview.setOnItemClickListener{parent, view, position, id ->

            val title = legArrayList[position].title
            val subTitle = legArrayList[position].subTitle
            val gifId = legArrayList[position].gifId
            val desc = legArrayList[position].desc

            val i = Intent(this, LegExerciseDescActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("gifId", gifId)
            i.putExtra("desc", desc)

            startActivity(i)
        }
    }
}