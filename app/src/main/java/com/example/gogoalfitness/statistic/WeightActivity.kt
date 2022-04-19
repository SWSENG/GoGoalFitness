package com.example.gogoalfitness.statistic

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.gogoalfitness.NavigationActivity
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivityAllHistoryBinding
import com.example.gogoalfitness.databinding.ActivityWeightBinding
import com.example.gogoalfitness.fragment.StatisticFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class WeightActivity : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyModel = ViewModelProvider(this).get(HistoryModel::class.java)
        val user = FirebaseAuth.getInstance().currentUser
        var weightStart = ""
        var weightCurrent = ""
        var weightChange = ""

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("UserWeight")
        }

        database.get()
            .addOnSuccessListener {
                    result->
                weightCurrent = result.child("weightCurrent").value.toString()
                weightStart = result.child("weightStart").value.toString()
                //weightChange = result.child("weightChange").value.toString()
                binding.tvWeightCurrent.text = weightCurrent
            }
            .addOnFailureListener{ex-> binding.tvWeightCurrent.text = ex.message}

        binding.btnRecordWeight.setOnClickListener(){
            var newWeight = binding.tfWeightRecord.text.toString()

            historyModel.weightCurrent.value = newWeight
            binding.tvWeightCurrent.text = newWeight

            database.child("weightCurrent").setValue(newWeight)
            database.child("weightChange").setValue(calWeightChange(newWeight,weightStart))
            finish();
        }

    }

    private fun calWeightChange (newWeight:String, startWeight:String) : String {

        return (newWeight.toInt() - startWeight.toInt()).toString()
    }
}