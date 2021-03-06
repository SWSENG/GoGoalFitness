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
import com.example.gogoalfitness.list.ShoulderList

class ShoulderAdapter(private val context:Activity, private val arrayList: ArrayList<ShoulderList>):ArrayAdapter<ShoulderList>
    (context, R.layout.activity_workout_list, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_workout_list, null)

        val imageView: ImageView = view.findViewById(R.id.listWorkoutPic)
        val title: TextView = view.findViewById(R.id.listWorkoutText)
        val subTitle: TextView = view.findViewById(R.id.timeOverListWorkout)

        Glide.with(context).load(arrayList[position].gifId).into(imageView)
        title.text = arrayList[position].title
        subTitle.text = arrayList[position].subTitle

        return view
    }
}