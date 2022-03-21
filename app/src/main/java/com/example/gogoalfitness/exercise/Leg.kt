package com.example.gogoalfitness.exercise

import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.LegModel

class Leg {
    companion object{
        fun defaultExerciseList():ArrayList<LegModel>{
            var exercisesList = ArrayList<LegModel>()

            val jumpSquat = LegModel(1,
                "180 Jump Squat",
                R.drawable.jumpsquat,
                false,
                false)
            exercisesList.add(jumpSquat)

            val butterflyStretch = LegModel(2,
                "Butterfly Stretch",
                R.drawable.butterflystretch,
                false,
                false)
            exercisesList.add(butterflyStretch)

            val ankleHops = LegModel(3,
                "Ankle Hops",
                R.drawable.anklehops,
                false,
                false)
            exercisesList.add(ankleHops)

            val boxerSquatPunch = LegModel(4,
                "Boxer Squat Punch",
                R.drawable.boxersquatpunch,
                false,
                false)
            exercisesList.add(boxerSquatPunch)

            val burpees = LegModel(5,
                "Burpees",
                R.drawable.burpees,
                false,
                false)
            exercisesList.add(burpees)

            return exercisesList
        }
    }
}





