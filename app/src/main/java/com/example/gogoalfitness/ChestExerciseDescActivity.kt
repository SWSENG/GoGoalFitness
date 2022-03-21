package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityExerciseDescBinding

class ChestExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getIntExtra("gifId", R.drawable.armraises)
        val desc = intent.getStringExtra("desc")

        binding.videoView.setImageResource(tutorial)
        binding.workoutTextDesc.text = desc
    }
}