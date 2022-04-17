package com.example.gogoalfitness

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.gogoalfitness.adapter.ArmAdapter
import com.example.gogoalfitness.databinding.ActivityArmBinding
import com.example.gogoalfitness.list.ArmList
import com.example.gogoalfitness.list.RecipeInfo
import com.google.firebase.database.*

class ArmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArmBinding
    private lateinit var armArrayList: ArrayList<ArmList>
    private lateinit var database: DatabaseReference
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        armArrayList = arrayListOf()
        listView = findViewById(R.id.Armlistview)
        database = FirebaseDatabase.getInstance().getReference("Workout").child("ArmWorkout")

        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (e in snapshot.children) {
                    val name = e.child("name").value.toString()
                    val subTitle = e.child("subTitle").value.toString()
                    val gifId = e.child("gifId").value.toString()
                    val desc = e.child("desc").value.toString()

                    armArrayList.add(ArmList(name, subTitle, gifId, desc))

                    Log.d(TAG, snapshot.child("name").value.toString())
                    val adapter = ArmAdapter(this@ArmActivity, armArrayList)
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

        binding.armStartBtn.setOnClickListener{
            val intent = Intent(this, StartArmWorkout::class.java)
            startActivity(intent)
        }

        binding.Armlistview.setOnItemClickListener{parent, view, position, id ->

            val title = armArrayList[position].title
            val subTitle = armArrayList[position].subTitle
            val gifId = armArrayList[position].gifId
            val desc = armArrayList[position].desc

            val i = Intent(this, ArmExerciseDescActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("gifId", gifId)
            i.putExtra("desc", desc)

            startActivity(i)
        }
    }


}