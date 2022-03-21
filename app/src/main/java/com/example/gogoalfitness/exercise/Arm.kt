package com.example.gogoalfitness.exercise

import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ArmModel

class Arm {
    companion object {
        fun defaultExerciseList(): ArrayList<ArmModel> {
            var exercisesList = ArrayList<ArmModel>()

            val bandReversePlank = ArmModel(
                1,
                "Band Reverse Plank",
                R.drawable.bandreverseplank,
                false,
                false
            )
            exercisesList.add(bandReversePlank)

            val bicepsStretch = ArmModel(
                2,
                "Biceps Stretch",
                R.drawable.bicepsstretch,
                false,
                false
            )
            exercisesList.add(bicepsStretch)

            val tabletopReversePike = ArmModel(
                3,
                "Tabletop Reverse Pike",
                R.drawable.tabletopreversepike,
                false,
                false
            )
            exercisesList.add(tabletopReversePike)

            val upDownPlank = ArmModel(
                4,
                "Up Down Plank",
                R.drawable.updownplank,
                false,
                false
            )
            exercisesList.add(upDownPlank)

            val vSitCurlPress = ArmModel(
                5,
                "V Sit Curl Press",
                R.drawable.vsitcurlpress,
                false,
                false
            )
            exercisesList.add(vSitCurlPress)

            return exercisesList
        }
    }
}





