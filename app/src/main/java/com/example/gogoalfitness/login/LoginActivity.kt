package com.example.gogoalfitness.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivityLoginBinding

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
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Login Successfull",
                    Toast.LENGTH_LONG).show();
            }
            .addOnFailureListener { ex->
                Toast.makeText(baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG).show();
            }

    }
}