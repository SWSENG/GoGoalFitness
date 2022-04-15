package com.example.gogoalfitness

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gogoalfitness.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
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
            val birthday = binding.edtResultBirthdate.text.toString()
            updateData(username, height, weight, gender, birthday)
        }
    }

    private fun getUserData() {
        val gender = intent.getStringExtra("Gender").toString()
        val birthdate = intent.getStringExtra("Birthdate").toString()
        val height = intent.getStringExtra("Height").toString()
        val weight = intent.getStringExtra("Weight").toString()


        binding.editTextTextPersonHeight.setText(height)
        binding.editTextTextPersonWeight.setText(weight)
    }

    private fun updateData(
        username: String,
        height: String,
        weight: String,
        gender: String,
        birthday: String
    ) {
        databaseReference = FirebaseDatabase.getInstance().getReference("userTable")
        val user = mapOf<String,String>(
            "username" to username,
            "height" to height,
            "weight" to weight,
            "gender" to gender,
            "birthday" to birthday
        )
        val currentuser = FirebaseAuth.getInstance().currentUser!!.uid

        if (currentuser != null) {
            databaseReference.child(currentuser).updateChildren(user).addOnSuccessListener {

                Toast.makeText(this,"Success to update", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this,"Failed to update", Toast.LENGTH_SHORT).show()
            }
        }
    }
}