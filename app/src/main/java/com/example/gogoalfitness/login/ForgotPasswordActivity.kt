package com.example.gogoalfitness.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gogoalfitness.R
import com.example.gogoalfitness.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarForgotPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()

        binding.btnSubmit.setOnClickListener() {
            val email: String =
                binding.editTextEmailAddressForgotPassword.text.toString().trim() { it <= ' ' }

            if (email.isEmpty()) {
                Toast.makeText(
                    baseContext, "Please enter email address.",
                    Toast.LENGTH_LONG
                ).show();
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        Toast.makeText(
                            baseContext, "Link sent to your email.",
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
    }
}