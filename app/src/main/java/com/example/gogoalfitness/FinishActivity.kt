package com.example.gogoalfitness

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.gogoalfitness.statistic.WorkoutHistory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_finish.*
import java.time.LocalDate
import java.time.LocalDateTime

class FinishActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val actionBar = supportActionBar
        val workName = intent.getStringExtra("WorkName").toString()
        val WorkMin = intent.getStringExtra("WorkMin").toString()
        val workCalories = intent.getStringExtra("WorkCalories").toString()
        val dateSave = LocalDate.now().toString()
        val timeDate = LocalDateTime.now().withNano(0).toString()
        database = FirebaseDatabase.getInstance().getReference("WorkoutHistory")
        var workoutInfo = WorkoutHistory(workName,WorkMin,workCalories,dateSave)
        Log.d("time",WorkMin)
        database.child(timeDate).setValue(workoutInfo)

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)//set back button
        }

        //set the back btn on toolbar
        toolbar_finish_activity.setNavigationOnClickListener {
            onBackPressed() //go back to main activity, because I finished the exercise activity
        }


        finish_btn.setOnClickListener{
            finish() // finish this activity and go back to main activity
        }
    }
}