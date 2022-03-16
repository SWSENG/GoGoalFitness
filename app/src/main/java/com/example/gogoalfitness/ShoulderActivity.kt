package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityShoulderBinding
import com.example.gogoalfitness.fragment.HomeFragment

class ShoulderActivity : AppCompatActivity() {

    private lateinit var binding :ActivityShoulderBinding
    private lateinit var shoulderArrayList: ArrayList<ShoulderList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoulderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.aboutus,
            R.drawable.aboutus,
            R.drawable.aboutus,
            R.drawable.aboutus,
            R.drawable.aboutus
        )

        val title = arrayOf(
            "Arm Raises",
            "RhomBoid Pulls",
            "Side Arm Raise",
            "Knee Push-Ups",
            "Arm Scissors"
        )

        val subTitle = arrayOf(
            "00:16",
            "x14",
            "00:16",
            "x14",
            "00:30"
        )

        val gifId = intArrayOf(
            R.drawable.armraises,
            R.drawable.armraises,
            R.drawable.armraises,
            R.drawable.armraises,
            R.drawable.armraises
        )

        val desc = arrayOf(
            "Arm Raises",
            "RhomBoid Pulls",
            "Side Arm Raise",
            "Knee Push-Ups",
            "Arm Scissors"
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