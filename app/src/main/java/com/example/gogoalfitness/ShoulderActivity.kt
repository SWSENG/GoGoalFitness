package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.adapter.ShoulderAdapter
import com.example.gogoalfitness.databinding.ActivityShoulderBinding
import com.example.gogoalfitness.list.ShoulderList

class ShoulderActivity : AppCompatActivity() {

    private lateinit var binding :ActivityShoulderBinding
    private lateinit var shoulderArrayList: ArrayList<ShoulderList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoulderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shoulderStartBtn.setOnClickListener{
            val intent = Intent(this, StartShoulderWorkout::class.java)
            startActivity(intent)
        }

        val imageId = intArrayOf(
            R.drawable.armraises,
            R.drawable.bearwalk,
            R.drawable.butterflydips,
            R.drawable.bigarmcircles,
            R.drawable.armswings
        )

        val title = arrayOf(
            "Arm Raises",
            "Bear Walk",
            "Butterfly Dips",
            "Big Arm Circles",
            "Arm Swings"
        )

        val subTitle = arrayOf(
            "00:30",
            "00:30",
            "00:30",
            "00:30",
            "00:30"
        )

        val gifId = intArrayOf(
            R.drawable.armraises,
            R.drawable.bearwalk,
            R.drawable.butterflydips,
            R.drawable.bigarmcircles,
            R.drawable.armswings
        )

        val desc = arrayOf(
            "1. Grab a set of dumbbells and stand straight. \n" +
                    "2. With your palms facing down, lift the dumbbells and raise your arms out to the sides. \n" +
                    "3. Once your elbows are at shoulder height, pause, and then slowly lower the arms back to the initial position. \n" +
                    "4. Repeat.",
            "1. Get down on your hands and feet with your knees slightly bent and your back flat.\n" +
                    "2. Walk your right hand and your left foot forward.\n" +
                    "3. Walk your left hand and your right foot forward.\n" +
                    "4. Keep walking and alternating sides until the set is complete.\n",
            "1. Sit on the mat with your feet together, place your hands behind you with your fingers facing forward, bend your knees and let your thighs fall open.\n" +
                    "2. Straighten your arms as you press your hips up and bring both knees together.\n" +
                    "3. Bend your elbows and lower your body back to the starting position.\n" +
                    "4. Repeat until the set is complete.",
            "1. Stand straight with your feet shoulder-width apart. Raise and extend your arms to the sides, without bending the elbows.\n" +
                    "2. Slowly rotate your arms forward, making big circles.\n" +
                    "3. Complete a set in one direction and then switch, rotating backward.",
            "1. Stand up straight with your knees slightly bent, your feet shoulder-width apart and your arms stretched horizontally to the sides.\n" +
                    "2. Cross your arms at the front and then quickly bring them back as far as you can.\n" +
                    "3. Repeat this back and forth movement until the set is complete.\n"
        )


        shoulderArrayList = ArrayList()

        for(i in title.indices)
        {
            val shoulderWorkout = ShoulderList(title[i], subTitle[i], imageId[i], gifId[i], desc[i])
            shoulderArrayList.add(shoulderWorkout)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = ShoulderAdapter(this, shoulderArrayList)
        binding.listview.setOnItemClickListener{parent, view, position, id ->

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