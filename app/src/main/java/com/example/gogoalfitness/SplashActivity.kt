package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gogoalfitness.fragment.HomeFragment

class SplashActivity : AppCompatActivity() {

    private val SPLASHTIME: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }, SPLASHTIME)
    }
}