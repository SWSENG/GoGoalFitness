package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.gogoalfitness.EditProfileActivity
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivitySignupBinding
import java.util.*

class SignupActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.datePicker.visibility = View.GONE

        binding.imageButtonCalendar.setOnClickListener(){
            if (binding.datePicker.visibility == View.VISIBLE)
                binding.datePicker.visibility = View.GONE
            else if (binding.datePicker.visibility == View.GONE)
                binding.datePicker.visibility = View.VISIBLE
        }

        val today = Calendar.getInstance()
        binding.datePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(
            Calendar.DAY_OF_MONTH)){
                view,year,month,day->
            val month = month+1

            binding.tvResultBirthdate.text = "$day/$month/$year"
        }



        binding.btnNext.setOnClickListener(){

            val gender:String = when (binding.rgGender.checkedRadioButtonId){
                R.id.radioButtonMale -> "Male"
                R.id.radioButtonFemale -> "Female"
                else -> ""
            }


            val intent = Intent(this,SignupActivity2::class.java)
            intent.putExtra("Gender",gender)
            intent.putExtra("Birthdate",binding.tvResultBirthdate.text.toString())
            intent.putExtra("Height",binding.editTextHeight.text.toString())
            intent.putExtra("Weight",binding.editTextCurrentWeight.text.toString())
            startActivity(intent)

            val intent2 = Intent(this,EditProfileActivity::class.java)
            intent2.putExtra("Gender",gender)
            intent2.putExtra("Birthdate",binding.tvResultBirthdate.text.toString())
            intent2.putExtra("Height",binding.editTextHeight.text.toString())
            intent2.putExtra("Weight",binding.editTextCurrentWeight.text.toString())
            startActivity(intent2)
        }
    }
}