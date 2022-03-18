package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityChestBinding

class ChestActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChestBinding
    private lateinit var chestArrayList: ArrayList<ChestList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.ankletappushups,
            R.drawable.asymmetricalpushup,
            R.drawable.cheststretch,
            R.drawable.declinepushup,
            R.drawable.aroundtheworlds
        )

        val title = arrayOf(
            "Ankle Tap Push Ups",
            "Asymmetrical Push Up",
            "Chest Stretch",
            "Decline Push Up",
            "Around The Worlds"
        )

        val subTitle = arrayOf(
            "00:30",
            "00:30",
            "00:30",
            "00:30",
            "00:30"
        )

        val gifId = intArrayOf(
            R.drawable.ankletappushups,
            R.drawable.asymmetricalpushup,
            R.drawable.cheststretch,
            R.drawable.declinepushup,
            R.drawable.aroundtheworlds
        )

        val desc = arrayOf(
            "1. Start in a push up position, with your hands under your shoulders and your legs extended back.\n" +
                    "2. Start bending your elbows and lower your chest until it’s just above the floor.\n" +
                    "3. As you push back to the starting position, lift your hips, and touch your left ankle with your right hand.\n" +
                    "4. Return to the push up position and repeat on the opposite side.\n" +
                    "5. Keep alternating sides until the set is complete.",
            "1. Start in a push up position with your legs extended back and the hands below the shoulders.\n" +
                    "2. Place your left forearm on the mat, with the elbow directly under the shoulder, and start bending your right elbow until your chest is just above the floor.\n" +
                    "3. Push back to the starting position, repeat, and then switch sides.\n" ,
            "1. With the side of your body facing a wall, place your left palm on the wall.\n" +
                    "2. Slowly rotate your torso to the right, until you feel the stretch in your chest and in your left shoulder.\n" +
                    "3. Hold for 15 to 30 seconds and repeat on the right side.",
            "1. Start in a plank position, with your wrists under your shoulders and your feet hip-width apart, and place your feet on top of a step.\n" +
                    "2. Bend your elbows and lower your chest until it’s just above the floor.\n" +
                    "3. Push back to the starting position, and repeat until the set is complete.",
            "1. Lie down on a mat with a dumbbell in each hand, your arms by your sides, and the palms of your hands facing the ceiling.\n" +
                    "2. Pull your arms away from the body in a semi-circular movement, and bring the dumbbells over your head.\n" +
                    "3. Reverse the movement to return to the starting position, and repeat."
        )


        chestArrayList = ArrayList()

        for(i in title.indices)
        {
            val chestWorkout = ChestList(title[i], subTitle[i], imageId[i], gifId[i], desc[i])
            chestArrayList.add(chestWorkout)
        }

        binding.Chestlistview.isClickable = true
        binding.Chestlistview.adapter = ChestAdapter(this, chestArrayList)
        binding.Chestlistview.setOnItemClickListener{parent, view, position, id ->

            val title = title[position]
            val subTitle = subTitle[position]
            val imageId = imageId[position]
            val gifId = gifId[position]
            val desc = desc[position]

            val i = Intent(this, ShoulderExerciseDescActivity::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("imageId", imageId)
            i.putExtra("gifId", gifId)
            i.putExtra("desc", desc)

            startActivity(i)
        }
    }
}