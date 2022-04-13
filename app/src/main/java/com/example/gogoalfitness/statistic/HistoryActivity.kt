package com.example.gogoalfitness.statistic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.databinding.ActivityHistoryBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewAllHistory.setOnClickListener(){

            val intent = Intent(this, AllHistoryActivity::class.java)

            startActivity(intent)
        }


    }
}