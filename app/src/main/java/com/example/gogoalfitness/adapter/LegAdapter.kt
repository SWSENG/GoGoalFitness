package com.example.gogoalfitness.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.LegList

class LegAdapter(private val context: Activity, private val arrayList: ArrayList<LegList>):
    ArrayAdapter<LegList>
    (context, R.layout.activity_leg_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_leg_list, null)

        val legImageView: ImageView = view.findViewById(R.id.LegListPic)
        val legTitle: TextView = view.findViewById(R.id.LegListText)
        val legSubTitle: TextView = view.findViewById(R.id.timeOverLegList)

        legImageView.setImageResource(arrayList[position].imageId)
        legTitle.text = arrayList[position].title
        legSubTitle.text = arrayList[position].subTitle

        return view
    }
}