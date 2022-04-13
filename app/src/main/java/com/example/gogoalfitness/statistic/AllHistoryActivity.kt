package com.example.gogoalfitness.statistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.databinding.ActivityAllHistoryBinding
import com.google.firebase.database.*

class AllHistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAllHistoryBinding
    private lateinit var database : DatabaseReference
    private lateinit var work : String
    private lateinit var workDay : String
    private lateinit var workTime : String
    private lateinit var workMin : String
    private lateinit var workCal : String
    private lateinit var formatTime : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val historyList:ArrayList<History> = ArrayList()
        database = FirebaseDatabase.getInstance().getReference("WorkoutHistory")
        database.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(workoutSnapshot in dataSnapshot.children)
                {
                    work = workoutSnapshot.child("workoutName").value.toString()
                    workDay = workoutSnapshot.child("workoutTime").value.toString()
                    workTime =  workoutSnapshot.key.toString()
                    workMin =  workoutSnapshot.child("minutes").value.toString()
                    workCal = workoutSnapshot.child("calories").value.toString()
                    historyList.add(History(work,workDay,workTime,workMin,workCal))
                    binding.allHistoryRecycleView.adapter = HistoryAdapter(historyList)
                    binding.allHistoryRecycleView.layoutManager = LinearLayoutManager(applicationContext)
                    binding.allHistoryRecycleView.setHasFixedSize(true)
                    //workoutSnapshot.getValue().toString()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("error", databaseError.toString())
            }
        })



    }
}