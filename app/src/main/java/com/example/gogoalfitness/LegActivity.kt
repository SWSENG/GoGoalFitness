package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityLegBinding
import com.example.gogoalfitness.databinding.ActivityShoulderBinding

class LegActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLegBinding
    private lateinit var legArrayList: ArrayList<LegList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.jumpsquat,
            R.drawable.butterflystretch,
            R.drawable.anklehops,
            R.drawable.boxersquatpunch,
            R.drawable.burpees
        )

        val title = arrayOf(
            "180 Jump Squat",
            "Butterfly Stretch",
            "Ankle Hops",
            "Boxer Squat Punch",
            "Burpees"
        )

        val subTitle = arrayOf(
            "00:30",
            "00:30",
            "00:30",
            "00:30",
            "00:30"
        )

        val gifId = intArrayOf(
            R.drawable.jumpsquat,
            R.drawable.butterflystretch,
            R.drawable.anklehops,
            R.drawable.boxersquatpunch,
            R.drawable.burpees
        )

        val desc = arrayOf(
            "1. Stand with your feet a little wider than shoulder-width apart, your toes pointing slightly outward, and sit back.\n" +
                    "2. Push through the heels to jump up, spinning to the left 180 degrees. 3. Land on your toes with your knees slightly bent and squat.\n" +
                    "4. Quickly jump up, spinning to the right, and go back into the squat position.\n" +
                    "5. Repeat until the set is complete." ,
            "1. Sit down on the floor and bring both feet together.\n" +
                    "2. With the help of your arms, drive your knees down into the floor.\n" +
                    "3. Hold the stretch for 20 to 30 seconds and then slowly release." ,
            "1. Stand straight with your hands by your sides and with your feet hip-width apart.\n" +
                    "2. Bounce off the floor in a quick, repetitive movement.\n" +
                    "3. Repeat the movement until the set is complete." ,
            "1. Start in a squat position, with your feet shoulder-width apart and your toes pointing forward.\n" +
                    "2. As you stand up, shift your weight to one leg and punch with the opposite arm.\n" +
                    "3. Squat and repeat the movement on the opposite side.\n",
            "1. Stand straight with your feet shoulder-width apart.\n" +
                    "2. Squat and place your hands in front of your feet.\n" +
                    "3. Jump back until your legs are fully extended and your body is in plank position.\n" +
                    "4. Do a push up, jump forward, and then push through the heels to return to the starting position.\n" +
                    "5. Repeat until the set is complete."
        )


        legArrayList = ArrayList()

        for(i in title.indices)
        {
            val legWorkout = LegList(title[i], subTitle[i], imageId[i], gifId[i], desc[i])
            legArrayList.add(legWorkout)
        }

        binding.Leglistview.isClickable = true
        binding.Leglistview.adapter = LegAdapter(this, legArrayList)
        binding.Leglistview.setOnItemClickListener{parent, view, position, id ->

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