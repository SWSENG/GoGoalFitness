package com.example.gogoalfitness.adapter

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ArmList
import com.squareup.picasso.Picasso
import pl.droidsonroids.gif.GifImageView

class ArmAdapter (private val context: Activity, private val arrayList: ArrayList<ArmList>):
    ArrayAdapter<ArmList>
        (context, R.layout.activity_workout_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_workout_list, null)

        val armImageView: ImageView = view.findViewById(R.id.listWorkoutPic)
        val armTitle: TextView = view.findViewById(R.id.listWorkoutText)
        val armSubTitle: TextView = view.findViewById(R.id.timeOverListWorkout)

        Glide.with(context).load(arrayList[position].gifId).into(armImageView)
        armTitle.text = arrayList[position].title
        armSubTitle.text = arrayList[position].subTitle

        return view
    }
}