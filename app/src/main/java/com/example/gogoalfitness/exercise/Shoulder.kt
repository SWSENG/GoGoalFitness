package com.example.gogoalfitness.exercise

import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ShoulderModel

class Shoulder {
    companion object{
        fun defaultExerciseList():ArrayList<ShoulderModel>{
            var exercisesList = ArrayList<ShoulderModel>()

            val armRaises = ShoulderModel(1,
                "Arm Raises",
                R.drawable.armraises,
                false,
                false)
            exercisesList.add(armRaises)

            val bearWalk = ShoulderModel(2,
                "Bear Walk",
                R.drawable.bearwalk,
                false,
                false)
            exercisesList.add(bearWalk)

            val butterFlydips = ShoulderModel(3,
                "Butterfly Dips",
                R.drawable.butterflydips,
                false,
                false)
            exercisesList.add(butterFlydips)

            val bigArmCircles = ShoulderModel(4,
                "Big Arm Circles",
                R.drawable.bigarmcircles,
                false,
                false)
            exercisesList.add(bigArmCircles)

            val armSwings = ShoulderModel(5,
                "Arm Swings",
                R.drawable.armswings,
                false,
                false)
            exercisesList.add(armSwings)

            return exercisesList
        }
    }
}





