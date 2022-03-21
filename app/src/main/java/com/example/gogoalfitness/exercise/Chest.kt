package com.example.gogoalfitness.exercise

import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ChestModel

class Chest {
    companion object {

        fun defaultExerciseList(): ArrayList<ChestModel> {
            var exercisesList = ArrayList<ChestModel>()

            val ankleTapPushUps = ChestModel(
                1,
                "Ankle Tap Push Ups",
                R.drawable.ankletappushups,
                false,
                false
            )
            exercisesList.add(ankleTapPushUps)

            val asymmetricalPushUp = ChestModel(
                2,
                "Asymmetrical Push Up",
                R.drawable.asymmetricalpushup,
                false,
                false
            )
            exercisesList.add(asymmetricalPushUp)

            val chestStretch = ChestModel(
                3,
                "Chest Stretch",
                R.drawable.cheststretch,
                false,
                false
            )
            exercisesList.add(chestStretch)

            val declinePushUp = ChestModel(
                4,
                "Decline Push Up",
                R.drawable.declinepushup,
                false,
                false
            )
            exercisesList.add(declinePushUp)

            val aroundTheWorlds = ChestModel(
                5,
                "Around The Worlds",
                R.drawable.aroundtheworlds,
                false,
                false
            )
            exercisesList.add(aroundTheWorlds)

            return exercisesList
        }
    }
}





