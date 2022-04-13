package com.example.gogoalfitness.statistic

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.gogoalfitness.databinding.ActivityAllHistoryBinding
import com.example.gogoalfitness.databinding.ActivityWeightBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class WeightActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var binding : ActivityWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyModel = ViewModelProvider(this).get(HistoryModel::class.java)
        database = FirebaseDatabase.getInstance().getReference("UserWeight")

        database.get()
            .addOnSuccessListener {
                    result-> binding.tvWeightCurrent.text =
                result.child("weightCurrent").value.toString()
            }
            .addOnFailureListener{ex-> binding.tvWeightCurrent.text = ex.message}

        binding.btnRecordWeight.setOnClickListener(){
            var newWeight = binding.tfWeightRecord.text.toString()
            historyModel.weightCurrent.value = newWeight
            binding.tvWeightCurrent.text = newWeight
            database.child("weightCurrent").setValue(newWeight)

            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }


    }


}