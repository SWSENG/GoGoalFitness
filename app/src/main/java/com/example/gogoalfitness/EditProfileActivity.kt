package com.example.gogoalfitness

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gogoalfitness.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityEditProfileBinding

    //firebase auth
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var databaseReference : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }

        user?.let {
            val uid = user.uid
            databaseReference = FirebaseDatabase.getInstance().getReference(uid).child("UserInfo")
        }

        binding.edtDatePicker.visibility = View.GONE

        binding.imageButtonCalendar.setOnClickListener(){
            if (binding.edtDatePicker.visibility == View.VISIBLE)
                binding.edtDatePicker.visibility = View.GONE
            else if (binding.edtDatePicker.visibility == View.GONE)
                binding.edtDatePicker.visibility = View.VISIBLE
        }

        val today = Calendar.getInstance()
        binding.edtDatePicker.init(today.get(Calendar.YEAR),today.get(Calendar.MONTH),today.get(
            Calendar.DAY_OF_MONTH)){
                view,year,month,day->
            val month = month+1

            binding.edtResultBirthdate.text = "$day/$month/$year"
        }

        getUserData()

        binding.SaveBtn.setOnClickListener {
            val username = binding.editTextTextPersonName.text.toString()
            val height = binding.editTextTextPersonHeight.text.toString()
            val weight = binding.editTextTextPersonWeight.text.toString()
            val gender:String = when (binding.radioGender.checkedRadioButtonId){
                R.id.radioMale -> "Male"
                R.id.radioFemale -> "Female"
                else -> ""
            }
            val birthdate = binding.edtResultBirthdate.text.toString()
            updateData(username, height, weight, gender, birthdate)
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserData() {
        databaseReference.get()
            .addOnSuccessListener {
                    result-> binding.editTextTextPersonName.setText(result.child("username").value.toString())
                binding.editTextTextPersonHeight.setText(result.child("height").value.toString())
                binding.editTextTextPersonWeight.setText(result.child("weight").value.toString())
                binding.edtResultBirthdate.text =
                    result.child("birthdate").value.toString()
            }
            .addOnFailureListener{

            }
    }

    private fun updateData(
        username: String,
        height: String,
        weight: String,
        gender: String,
        birthdate: String
    ) {
        val user = mapOf<String,String>(
            "username" to username,
            "height" to height,
            "weight" to weight,
            "gender" to gender,
            "birthday" to birthdate
        )

        databaseReference.child("username").setValue(username)
        databaseReference.child("height").setValue(height)
        databaseReference.child("weight").setValue(weight)
        databaseReference.child("gender").setValue(gender)
        databaseReference.child("birthdate").setValue(birthdate)
    }
}