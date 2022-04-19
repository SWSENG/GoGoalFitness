package com.example.gogoalfitness.statistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.databinding.ActivityAllHistoryBinding
import com.google.firebase.auth.FirebaseAuth
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
    private var historyList = arrayListOf<History>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser
        val date = intent.getStringExtra("historyDate")

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("WorkOutHistory")
        }

        if(date != null){
            getDateHistory(date)
            binding.tvDateHistory.visibility = View.VISIBLE
            binding.tvHistoryLabel.visibility = View.VISIBLE
            binding.tvDateHistory.text = date.toString()
        }
        else{
            binding.tvDateHistory.visibility = View.GONE
            binding.tvHistoryLabel.visibility = View.GONE
            getAllHistory()
        }

    }

    private fun getAllHistory(){
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
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("error", databaseError.toString())
            }
        })


    }

    private fun getDateHistory(date:String){
        database.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for(workoutSnapshot in dataSnapshot.children)
                {
                    Log.d("date",date)
                    workDay = workoutSnapshot.child("workoutTime").value.toString()
                    if(workDay == date){
                        work = workoutSnapshot.child("workoutName").value.toString()
                        workTime =  workoutSnapshot.key.toString()
                        workMin =  workoutSnapshot.child("minutes").value.toString()
                        workCal = workoutSnapshot.child("calories").value.toString()
                        historyList.add(History(work,workDay,workTime,workMin,workCal))
                        binding.allHistoryRecycleView.adapter = HistoryAdapter(historyList)
                        binding.allHistoryRecycleView.layoutManager = LinearLayoutManager(applicationContext)
                        binding.allHistoryRecycleView.setHasFixedSize(true)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("error", databaseError.toString())
            }
        })


    }

}