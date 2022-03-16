package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActicityUpperBodyListBinding
import com.example.gogoalfitness.databinding.ActivityShoulderExerciseDescBinding

class ShoulderExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoulderExerciseDescBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoulderExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getIntExtra("gifId", R.drawable.armraises)
        val desc = intent.getStringExtra("desc")

        binding.videoView.setImageResource(tutorial)
        binding.ShoulderWorkoutTextDesc.text = desc
    }
}