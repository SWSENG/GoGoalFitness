package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.gogoalfitness.EditProfileActivity
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivitySignupBinding
import com.example.gogoalfitness.databinding.ActivitySignupdetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class SignupDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupdetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupdetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSignup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        binding.datePicker.visibility = View.GONE

        binding.imageButtonCalendar.setOnClickListener() {
            if (binding.datePicker.visibility == View.VISIBLE)
                binding.datePicker.visibility = View.GONE
            else if (binding.datePicker.visibility == View.GONE)
                binding.datePicker.visibility = View.VISIBLE
        }

        val today = Calendar.getInstance()
        binding.datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(
                Calendar.DAY_OF_MONTH
            )
        ) { view, year, month, day ->
            val month = month + 1

            binding.tvResultBirthdate.text = "$day/$month/$year"
        }



        binding.btnNext.setOnClickListener() {

            val gender: String = when (binding.rgGender.checkedRadioButtonId) {
                R.id.radioButtonMale -> "Male"
                R.id.radioButtonFemale -> "Female"
                else -> ""
            }
            val username = intent.getStringExtra("username").toString()
            val birthdate = binding.tvResultBirthdate.text.toString()
            val height = binding.editTextHeight.text.toString()
            val weight = binding.editTextCurrentWeight.text.toString()

            var newUser = UserInfo(username, gender, birthdate, height, weight)
            addNewUserInfo(newUser)
            val intent = Intent(this, AccountCreatedActivity::class.java)
            startActivity(intent)

        }
    }

    private fun addNewUserInfo(newUser: UserInfo) {
        val user = auth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("UserInfo")
        }

        Toast.makeText(
            baseContext, "adding new",
            Toast.LENGTH_LONG
        ).show();


        database.setValue(newUser)
            .addOnSuccessListener {
                Toast.makeText(
                    baseContext, "User info added",
                    Toast.LENGTH_LONG
                ).show();

            }
            .addOnFailureListener { ex ->
                Toast.makeText(
                    baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG
                ).show();
            }


    }

}