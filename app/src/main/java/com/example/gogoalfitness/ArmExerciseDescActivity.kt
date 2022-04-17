package com.example.gogoalfitness

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.gogoalfitness.databinding.ActivityExerciseDescBinding
import com.squareup.picasso.Picasso

class ArmExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val tutorial = intent.getStringExtra("gifId")
        val desc = intent.getStringExtra("desc")

        Glide.with(applicationContext).load(tutorial).into(binding.videoView)
        Log.d(TAG,tutorial.toString())
        binding.workoutTextDesc.text = desc

        binding.workoutTextDesc.text =
            (binding.workoutTextDesc.text as String).replace("\\n", "\n")

    }
}