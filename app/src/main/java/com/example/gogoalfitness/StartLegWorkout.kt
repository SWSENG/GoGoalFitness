package com.example.gogoalfitness

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gogoalfitness.adapter.LegStatusAdapter
import com.example.gogoalfitness.exercise.Leg
import com.example.gogoalfitness.list.LegModel
import kotlinx.android.synthetic.main.activity_start_workout.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*

class StartLegWorkout : AppCompatActivity() {

    private var restTimer : CountDownTimer? = null
    private var restProgress = 0
    private var restTime : Long = 10

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0
    private var exerciseTime : Long = 30

    private var legList : ArrayList<LegModel>? = null
    private var currentExercisePosition = -1

    private var legAdapter : LegStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)

        }

        legList = Leg.defaultExerciseList()
        setupRestView()

        setupExerciseStatusRecyclerView()

    }

    // rest
    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(restTime*1000, 1000){

            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = restTime.toInt() - restProgress
                timer_tv.text = (restTime - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                legList!![currentExercisePosition].setIsSelected(true)
                legAdapter!!.notifyDataSetChanged() //update the adapter about change the data
                setupExerciseView()
            }
        }.start()
    }

    private fun setupRestView(){

        rest_view_ll.visibility = View.VISIBLE
        shoulder_view_ll.visibility = View.GONE

        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        next_exercise_tv.text = legList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }

    // exercise
    private fun setExerciseProgressBar(){
        shoulder_progressBar.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTime*1000, 1000){

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                shoulder_progressBar.progress = exerciseTime.toInt() - exerciseProgress
                shoulder_timer_tv.text = (exerciseTime - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < legList?.size!! - 1){
                    legList!![currentExercisePosition].setIsSelected(false)
                    legList!![currentExercisePosition].setIsCompleted(true)
                    legAdapter!!.notifyDataSetChanged() //update the adapter about change the data
                    setupRestView()
                }else{
                    finish()
                    val intent = Intent(this@StartLegWorkout, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setupExerciseView(){

        shoulder_view_ll.visibility = View.VISIBLE
        rest_view_ll.visibility = View.GONE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()


        // setting exercise values
        image_iv.setImageResource(legList!![currentExercisePosition].getImage())
        shoulder_name_tv.text = legList!![currentExercisePosition].getName()
    }


    private fun setupExerciseStatusRecyclerView(){
        //set the recycler view in horizontal way
        shoulder_status_rv.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)

        legAdapter = LegStatusAdapter(legList!!,this)
        shoulder_status_rv.adapter = legAdapter
    }

    override fun onDestroy() {
        // rest view
        if(restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        // exercise view
        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        super.onDestroy()
    }

    override fun onBackPressed() {
        customDialogForBackButton()
    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        // YES
        customDialog.yes_btn.setOnClickListener {
            finish() // go back to main activity
            customDialog.dismiss() // close the dialog
        }
        // NO
        customDialog.no_btn.setOnClickListener {
            customDialog.dismiss() // close the dialog
        }

        customDialog.show()
    }
}