package com.example.gogoalfitness.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ChestList

class ChestAdapter (private val context: Activity, private val arrayList: ArrayList<ChestList>):
    ArrayAdapter<ChestList>
        (context, R.layout.activity_workout_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_workout_list, null)

        val chestImageView: ImageView = view.findViewById(R.id.listWorkoutPic)
        val chestTitle: TextView = view.findViewById(R.id.listWorkoutText)
        val chestSubTitle: TextView = view.findViewById(R.id.timeOverListWorkout)

        Glide.with(context).load(arrayList[position].gifId).into(chestImageView)
        chestTitle.text = arrayList[position].title
        chestSubTitle.text = arrayList[position].subTitle

        return view
    }
}