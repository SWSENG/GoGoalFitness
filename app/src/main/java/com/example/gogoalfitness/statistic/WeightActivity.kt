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
    private lateinit var binding : ActivityWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historyModel = ViewModelProvider(this).get(HistoryModel::class.java)
        val user = FirebaseAuth.getInstance().currentUser
        var weightStart = ""
        var weightCurrent = ""
        var height = ""

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid)
        }

        database.get()
            .addOnSuccessListener {
                    result->
                weightCurrent = result.child("UserWeight").child("weightCurrent").value.toString()
                weightStart = result.child("UserWeight").child("weightStart").value.toString()
                height = result.child("UserInfo").child("height").value.toString()
                binding.tvWeightCurrent.text = weightCurrent
                binding.tvHeightCurrent.text = height
                val value = calBMI(weightCurrent.toDouble(), height.toDouble())
                val bmiValue = String.format("%.1f", value).toDouble()
                binding.tvBmiValue.setText(bmiValue.toString())
                binding.tvWeightStatus.setText(weightStatus(bmiValue))
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

    private fun calBMI(weight:Double, height:Double):Double {
        val bmiValue = (weight / ((height / 100) * (height / 100)));
        return  bmiValue
    }

    private fun weightStatus(bmi:Double):String {
        if (bmi < 18.5) {
            return "Underweight"
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Healthy Weight Range";
        } else if (bmi >= 25 && bmi < 30){
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}