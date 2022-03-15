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
        setContentView(R.layout.activity_shoulder)

        val imageId = intArrayOf(

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

        shoulderArrayList = ArrayList()

        for(i in title.indices)
        {
            val shoulderWorkout = ShoulderList(title[i], subTitle[i], imageId[i])
            shoulderArrayList.add(shoulderWorkout)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = ShoulderAdapter(this, shoulderArrayList)
        binding.listview.setOnItemClickListener{parent, view, position, id ->

            val title = title[position]
            val subTitle = subTitle[position]
            val imageId = imageId[position]

            val i = Intent(this, HomeFragment::class.java)
            i.putExtra("title", title)
            i.putExtra("subTitle", subTitle)
            i.putExtra("imageId", imageId)

            startActivity(i)
        }
    }
}