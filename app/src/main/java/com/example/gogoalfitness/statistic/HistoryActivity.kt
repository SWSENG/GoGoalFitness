package com.example.gogoalfitness.statistic

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.example.gogoalfitness.databinding.ActivityHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.time.LocalDate
import kotlin.collections.HashSet

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHistoryBinding
    private lateinit var database : DatabaseReference
    private lateinit var workDay : String
    private lateinit var calDay : CalendarDay
    private lateinit var visibleDay : LocalDate
    private var historyHash = hashSetOf<CalendarDay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("WorkOutHistory")
        }

        database.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(workoutSnapshot in dataSnapshot.children)
                {

                    workDay = workoutSnapshot.child("workoutTime").value.toString()
                    visibleDay = LocalDate.parse(workDay)
                    val d = visibleDay.dayOfMonth
                    val m = visibleDay.monthValue
                    val y = visibleDay.year
                    calDay = CalendarDay.from(y,m,d)
                    historyHash.add(calDay)

                }
                binding.calendarView.addDecorator(AllDaysDisabledDecorator())
                binding.calendarView.addDecorator(AvailableDaysDecorator(Color.DKGRAY, historyHash))
            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.d("error", databaseError.toString())
            }
        })

        binding.calendarView.setOnDateChangedListener(OnDateSelectedListener
        { widget, date, selected ->

            val y = date.year
            val m = date.month
            val d = date.day
            var day = ""
            var month = ""

            if(d < 10)
                day = "0$d"
            else
                day = "$d"

            if((m) < 10)
                month = "0$m"
            else
                month = "$m"

            val historyDate = "$y-$month-$day"
            val intent = Intent(this, AllHistoryActivity::class.java)
            Log.d("date",historyDate.toString())
            intent.putExtra("historyDate", historyDate)
            startActivity(intent)
        })


        binding.btnViewAllHistory.setOnClickListener(){

            val intent = Intent(this, AllHistoryActivity::class.java)

            startActivity(intent)
        }


    }

    class AllDaysDisabledDecorator : DayViewDecorator {
        override fun shouldDecorate(day: CalendarDay?): Boolean {
            return true //decorate all days in calendar
        }

        override fun decorate(view: DayViewFacade) {
            view.setDaysDisabled(true) //disable all days
        }
    }

    class AvailableDaysDecorator (_color:Int, dates: HashSet<CalendarDay> ): DayViewDecorator {
        var calendarCollection:HashSet<CalendarDay> = dates
        val color = _color
        override fun shouldDecorate(day: CalendarDay): Boolean {

            return calendarCollection.contains(day) //decorate only available days
        }

        override fun decorate(view: DayViewFacade) {
            view.setDaysDisabled(false) ///important to enable day
            view.addSpan(DotSpan(5f, color)) //adds small dot to bottom of day number text
        }
    }
}