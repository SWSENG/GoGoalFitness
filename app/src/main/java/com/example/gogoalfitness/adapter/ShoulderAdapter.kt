package com.example.gogoalfitness.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.gogoalfitness.R
import com.example.gogoalfitness.list.ShoulderList

class ShoulderAdapter(private val context:Activity, private val arrayList: ArrayList<ShoulderList>):ArrayAdapter<ShoulderList>
    (context, R.layout.acticity_upper_body_list, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.acticity_upper_body_list, null)

        val imageView: ImageView = view.findViewById(R.id.overheadPressesPic)
        val title: TextView = view.findViewById(R.id.overheadPressesText)
        val subTitle: TextView = view.findViewById(R.id.timeOverheadPresses)

        imageView.setImageResource(arrayList[position].imageId)
        title.text = arrayList[position].title
        subTitle.text = arrayList[position].subTitle

        return view
    }
}