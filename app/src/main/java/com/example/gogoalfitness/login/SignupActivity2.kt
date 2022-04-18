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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSignup2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        //if no exist in database auto create for us

        binding.btnSignUpSignUp.setOnClickListener(){
            val email : String = binding.editTextEmailAddressSignUp.text.toString()
            val password : String = binding.editTextPasswordSignUp.text.toString()

            registerNewUser(email, password)

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

}