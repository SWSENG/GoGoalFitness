package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivityAccountCreatedBinding

class AccountCreatedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountCreatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountCreatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(){
            //to home page/home fragment
            //val intent = Intent(this,Home::class.java)
            //startActivity(intent)
        }
    }
}