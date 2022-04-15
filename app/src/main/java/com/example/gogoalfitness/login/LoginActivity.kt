package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gogoalfitness.NavigationActivity
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogInLogIn.setOnClickListener(){
            val email:String = binding.editTextEmailAddressLogin.text.toString()
            val password:String = binding.editTextPasswordLogin.text.toString()
            loginUser(email,password)
        }

        binding.tvForgotPassword.setOnClickListener(){
            val intent = Intent(this,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Login Successfull",
                    Toast.LENGTH_LONG).show();

                //to home page home fragment
                val intent = Intent(this,NavigationActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { ex->
                Toast.makeText(baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG).show();
            }

    }
}