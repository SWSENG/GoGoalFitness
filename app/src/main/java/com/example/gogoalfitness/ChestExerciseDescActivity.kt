package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityChestExerciseDescBinding

class ChestExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChestExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChestExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getIntExtra("gifId", R.drawable.armraises)
        val desc = intent.getStringExtra("desc")

        binding.chestVideoView.setImageResource(tutorial)
        binding.chestWorkoutTextDesc.text = desc
    }
}