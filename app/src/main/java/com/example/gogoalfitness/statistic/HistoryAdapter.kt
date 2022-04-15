package com.example.gogoalfitness.statistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gogoalfitness.R
import kotlinx.android.synthetic.main.history_view.view.*

class HistoryAdapter(private val historyList:ArrayList<History>) :RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){

    class HistoryViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var tvHisDate = view.tvHisDate
        var tvHisTime = view.tvHisTime
        var tvHisWorkout = view.tvHisWorkout
        var tvHisMins = view.tvHisMins
        var tvHisCal = view.tvHisCal
        var imgHistory = view.imgHistory
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_view,parent,false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        var path = ""
        val currentRec = historyList[position]
        holder.tvHisDate.text = currentRec.dayWorkout
        //holder.tvHisTime.text = currentRec.timeWorkout
        holder.tvHisWorkout.text = currentRec.workout
        holder.tvHisMins.text = currentRec.minWorkout
        holder.tvHisCal.text = currentRec.caloryWorkout
        if (currentRec.workout == "chest")
            holder.imgHistory.setImageResource(R.drawable.chest)
        else if (currentRec.workout == "shoulder")
            holder.imgHistory.setImageResource(R.drawable.upperbody)
        else if (currentRec.workout == "arm")
            holder.imgHistory.setImageResource(R.drawable.arm)
        else if (currentRec.workout == "leg")
            holder.imgHistory.setImageResource(R.drawable.lowerbody)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

}