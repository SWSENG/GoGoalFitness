package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityLegExerciseDescBinding
import com.example.gogoalfitness.databinding.ActivityShoulderExerciseDescBinding

class LegExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLegExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLegExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getIntExtra("gifId", R.drawable.armraises)
        val desc = intent.getStringExtra("desc")

        binding.legVideoView.setImageResource(tutorial)
        binding.LegWorkoutTextDesc.text = desc
    }
}