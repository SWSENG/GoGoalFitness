package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gogoalfitness.databinding.ActivitySignup2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySignup2Binding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //if no exist in database auto create for us

        binding.btnSignUpSignUp.setOnClickListener(){
            val email : String = binding.editTextEmailAddressSignUp.text.toString()
            val password : String = binding.editTextPasswordSignUp.text.toString()

            //insert user info into realtime database
            //username,gender,birthdate,height,weight
            val username = binding.editTextUsername.text.toString()
//            val gender = intent.getStringExtra("Gender").toString()
//            val birthdate = intent.getStringExtra("Birthdate").toString()
//            val height = intent.getStringExtra("Height").toString()
//            val weight = intent.getStringExtra("Weight").toString()

//            var newUser = UserInfo(username,gender,birthdate,height,weight)
            registerNewUser(email, password)
//            addNewUserInfo(newUser)

        }

    }

    private fun registerNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                Toast.makeText(baseContext, "New user added",
                    Toast.LENGTH_LONG).show();

                val intent = Intent(this,SignupActivity::class.java)
                intent.putExtra("username",binding.editTextUsername.text.toString())
                startActivity(intent)
            }
            .addOnFailureListener { ex->

                Toast.makeText(baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG).show();
            }

    }

//    private fun addNewUserInfo(newUser: UserInfo) {
//        val user = auth.currentUser
//        if (user != null) {
//            // User is signed in
//        } else {
//            // No user is signed in
//        }
//
//        user?.let {
//            val uid = user.uid
//            database = FirebaseDatabase.getInstance().getReference(uid).child("UserInfo")
//        }
//
//        Toast.makeText(baseContext, "adding new",
//            Toast.LENGTH_LONG).show();
//
//
//        database.setValue(newUser)
//            .addOnSuccessListener {
//                Toast.makeText(baseContext, "User info added",
//                    Toast.LENGTH_LONG).show();
//
//            }
//            .addOnFailureListener { ex->
//                Toast.makeText(baseContext, ex.message.toString(),
//                    Toast.LENGTH_LONG).show();
//            }
//
//
//    }
}