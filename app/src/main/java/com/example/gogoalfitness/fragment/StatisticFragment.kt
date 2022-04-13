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
import com.google.firebase.database.*

class StatisticFragment : Fragment() {
    private lateinit var database : DatabaseReference
    private lateinit var weightDatabase : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentStatisticBinding.inflate(layoutInflater)
        var totalWork = 0
        var totalMin = 0
        var totalCal = 0
        var weightStart = ""
        var weightCurrent = ""
        var weightChange = 0
        val historyModel = ViewModelProvider(this).get(HistoryModel::class.java)

        database = FirebaseDatabase.getInstance().getReference("WorkoutHistory")
        weightDatabase =  FirebaseDatabase.getInstance().getReference("UserWeight")

        database.addValueEventListener( object : ValueEventListener {
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

        weightDatabase.get()
            .addOnSuccessListener {
                    result-> weightStart = result.child("weightStart").value.toString()
                weightCurrent = result.child("weightCurrent").value.toString()
                weightChange = weightCurrent.toInt() - weightStart.toInt()
                historyModel.weightChange.value = weightChange.toString()
                historyModel.weightStart.value = weightStart.toString()
                historyModel.weightCurrent.value = weightCurrent.toString()
            }
            .addOnFailureListener{ex-> bind.tvStartWeight.text = ex.message}


        weightStart = bind.tvStartWeight.text.toString()

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
}