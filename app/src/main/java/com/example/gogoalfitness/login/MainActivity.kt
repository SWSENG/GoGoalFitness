package com.example.gogoalfitness.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gogoalfitness.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()

        //when no logout keep the user loging in
        /*
        if (auth.currentUser != null){
            //if login straight to home page
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }
        */

        //logout

        /*
        logoutCurrentUser()

        private fun logoutCurrentUser() {
            auth.signOut()
            Toast.makeText(baseContext, "signed out",
                    Toast.LENGTH_LONG).show();
        }
        */

        binding.btnLogInMain.setOnClickListener() {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener() {
            val intent = Intent(this,SignupActivity2::class.java)
            startActivity(intent)
        }
    }
}