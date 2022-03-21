package com.example.gogoalfitness.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ArmList

class ArmAdapter (private val context: Activity, private val arrayList: ArrayList<ArmList>):
    ArrayAdapter<ArmList>
        (context, R.layout.activity_arm_list, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.activity_arm_list, null)

        val armImageView: ImageView = view.findViewById(R.id.ArmListPic)
        val armTitle: TextView = view.findViewById(R.id.ArmListText)
        val armSubTitle: TextView = view.findViewById(R.id.timeOverArmList)

        armImageView.setImageResource(arrayList[position].imageId)
        armTitle.text = arrayList[position].title
        armSubTitle.text = arrayList[position].subTitle

        return view
    }
}