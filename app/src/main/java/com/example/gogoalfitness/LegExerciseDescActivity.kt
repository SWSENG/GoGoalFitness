package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.gogoalfitness.databinding.ActivityExerciseDescBinding

class LegExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getStringExtra("gifId")
        val desc = intent.getStringExtra("desc")

        Glide.with(applicationContext).load(tutorial).into(binding.videoView)
        binding.workoutTextDesc.text = desc

        binding.workoutTextDesc.text =
            (binding.workoutTextDesc.text as String).replace("\\n", "\n")
    }
}