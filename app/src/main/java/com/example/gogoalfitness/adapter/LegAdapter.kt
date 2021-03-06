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
import com.example.gogoalfitness.list.LegList

class LegAdapter(private val context: Activity, private val arrayList: ArrayList<LegList>):
    ArrayAdapter<LegList>
    (context, R.layout.activity_workout_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_workout_list, null)

        val legImageView: ImageView = view.findViewById(R.id.listWorkoutPic)
        val legTitle: TextView = view.findViewById(R.id.listWorkoutText)
        val legSubTitle: TextView = view.findViewById(R.id.timeOverListWorkout)

        Glide.with(context).load(arrayList[position].gifId).into(legImageView)
        legTitle.text = arrayList[position].title
        legSubTitle.text = arrayList[position].subTitle

        return view
    }
}