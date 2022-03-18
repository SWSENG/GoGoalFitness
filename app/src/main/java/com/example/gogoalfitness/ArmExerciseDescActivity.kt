package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityArmExerciseDescBinding

class ArmExerciseDescActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArmExerciseDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArmExerciseDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tutorial = intent.getIntExtra("gifId", R.drawable.armraises)
        val desc = intent.getStringExtra("desc")

        binding.armVideoView.setImageResource(tutorial)
        binding.armWorkoutTextDesc.text = desc
    }
}