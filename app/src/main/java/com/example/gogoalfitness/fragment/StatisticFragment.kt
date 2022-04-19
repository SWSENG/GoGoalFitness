package com.example.gogoalfitness.fragment

import android.content.Intent
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.databinding.FragmentStatisticBinding
import com.example.gogoalfitness.statistic.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StatisticFragment : Fragment() {
    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentStatisticBinding.inflate(layoutInflater)
        var totalWork = 0
        var totalMin = 0
        var totalCal = 0
        var weightStart:String? = ""
        var weightCurrent = ""
        var weightChange = ""
        var initWeight = ""
        val historyModel = ViewModelProvider(this).get(HistoryModel::class.java)
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid)
        }

        database.child("WorkOutHistory").addValueEventListener( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (workoutSnapshot in dataSnapshot.children) {
                    totalWork += 1
                    var min = workoutSnapshot.child("minutes").value.toString()
                    var cal = workoutSnapshot.child("calories").value.toString()
                    totalMin += min.toInt()
                    totalCal += cal.toInt()
                    historyModel.totalWorkout.value = totalWork.toString()
                    historyModel.totalMin.value = totalMin.toString()
                    historyModel.totalCalories.value = totalCal.toString()

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.d("error", databaseError.toString())
            }
        })

        database.get()
            .addOnSuccessListener {
                    result->
                weightStart = result.child("UserWeight").child("weightStart").value.toString()
                weightCurrent = result.child("UserWeight").child("weightCurrent").value.toString()
                weightChange = result.child("UserWeight").child("weightChange").value.toString()
                initWeight = result.child("UserInfo").child("weight").value.toString()

                if(weightStart == "null"){
                    database.child("UserWeight").child("weightStart").setValue(initWeight)
                    database.child("UserWeight").child("weightCurrent").setValue(initWeight)
                    database.child("UserWeight").child("weightChange").setValue("0")
                    historyModel.weightStart.value = initWeight
                    historyModel.weightCurrent.value = initWeight
                    historyModel.weightChange.value = "0"
                    //Log.d("check1", weightStart.toString())
                }
                else if(weightStart != initWeight){
                    var _weightChange = calWeightChange(weightCurrent,initWeight)

                    database.child("UserWeight").child("weightStart").setValue(initWeight)
                    database.child("UserWeight").child("weightChange").setValue(_weightChange)
                    historyModel.weightStart.value = initWeight
                    historyModel.weightCurrent.value = weightCurrent
                    historyModel.weightChange.value = _weightChange
                }
                else{
                    historyModel.weightStart.value = weightStart
                    historyModel.weightCurrent.value = weightCurrent
                    historyModel.weightChange.value = weightChange
                }
            }
            .addOnFailureListener{ex-> bind.tvStartWeight.text = ex.message}

        historyModel.totalCalories.observe(viewLifecycleOwner, { newValue ->
            bind.tvTotalCalory.text = newValue
        })

        historyModel.totalWorkout.observe(viewLifecycleOwner, { newValue ->
            bind.tvTotalWorkOut.text = newValue
        })

        historyModel.totalMin.observe(viewLifecycleOwner, { newValue ->
            bind.tvTotalWorkOutTime.text = newValue
        })

        historyModel.weightChange.observe(viewLifecycleOwner, { newValue ->
            bind.tvChangeWeight.text = newValue
        })

        historyModel.weightCurrent.observe(viewLifecycleOwner, { newValue ->
            bind.tvLatestWeight.text = newValue
        })

        historyModel.weightStart.observe(viewLifecycleOwner, { newValue ->
            bind.tvStartWeight.text = newValue
        })

        bind.btnHistory.setOnClickListener {
            val intent = Intent(this@StatisticFragment.requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        bind.btnWeightDetail.setOnClickListener {
            val intent = Intent(this@StatisticFragment.requireContext(), WeightActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }

    private fun calWeightChange (newWeight:String, startWeight:String) : String {

        return (newWeight.toInt() - startWeight.toInt()).toString()
    }
}