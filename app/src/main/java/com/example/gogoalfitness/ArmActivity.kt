package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.adapter.ArmAdapter
import com.example.gogoalfitness.databinding.ActivityArmBinding
import com.example.gogoalfitness.list.ArmList

class ArmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArmBinding
    private lateinit var armArrayList: ArrayList<ArmList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.bandreverseplank,
            R.drawable.bicepsstretch,
            R.drawable.tabletopreversepike,
            R.drawable.updownplank,
            R.drawable.vsitcurlpress
        )

        val title = arrayOf(
            "Band Reverse Plank",
            "Biceps Stretch",
            "Tabletop Reverse Pike",
            "Up Down Plank",
            "V Sit Curl Press"
        )

        val subTitle = arrayOf(
            "00:30",
            "00:30",
            "00:30",
            "00:30",
            "00:30"
        )

        val gifId = intArrayOf(
            R.drawable.bandreverseplank,
            R.drawable.bicepsstretch,
            R.drawable.tabletopreversepike,
            R.drawable.updownplank,
            R.drawable.vsitcurlpress
        )

        val desc = arrayOf(
            "1. Sit on the mat with your legs extended, place a band around your waist, extend your arms back with your fingers facing the body, and secure the band under your hands.\n" +
                    "2. Lift your butt off the mat and squeeze the glutes.\n" +
                    "3. Lower the hips to return to the starting position and repeat." ,
            "1. Stand straight and clasp your hands behind your back.\n" +
                    "2. Straighten your arms and rotate your hands, so that the palms face downward.\n" +
                    "3. Raise your arms and hold for 10 to 20 seconds." ,
            "1. Sit on the mat with your knees bent, your arms extended back, your fingers facing the body, and your feet hip-width apart.\n" +
                    "2. Lift your butt off the mat, coming into a tabletop position.\n" +
                    "3. Lower your hips, straighten the legs and lengthen the spine.\n" +
                    "4. Raise your hips, lift your torso and return to the tabletop position.\n" +
                    "5. Repeat." ,
            "1. Start in a plank position, with your wrists under your shoulders and your feet hip-width apart.\n" +
                    "2. Bend your left arm, place your left elbow on the mat and then bend your right arm and place your right elbow on the mat.\n" +
                    "3. Place your left hand on the mat, straighten your left arm and then place your right hand on the mat and straighten your right arm.\n" +
                    "4. Switch sides and repeat this up and down movement until the set is complete.",
            "1. Hold a dumbbell in each hand, sit on the mat and raise your legs to a 45-degree angle.\n" +
                    "2. Squeeze the biceps, lift the dumbbells and curl.\n" +
                    "3. Rotate your palms to the front, push the dumbbells up and fully extend your arms.\n" +
                    "4. Lower your arms back to the starting position and repeat until the set is complete."
        )


        armArrayList = ArrayList()

        for(i in title.indices)
        {
            val armWorkout = ArmList(title[i], subTitle[i], imageId[i], gifId[i], desc[i])
            armArrayList.add(armWorkout)
        }

        binding.Armlistview.isClickable = true
        binding.Armlistview.adapter = ArmAdapter(this, armArrayList)
        binding.Armlistview.setOnItemClickListener{parent, view, position, id ->

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