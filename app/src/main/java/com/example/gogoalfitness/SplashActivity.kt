package com.example.gogoalfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gogoalfitness.fragment.HomeFragment
import com.example.gogoalfitness.fragment.StatisticFragment
import com.example.gogoalfitness.login.SignupActivity2
import com.example.gogoalfitness.statistic.HistoryActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASHTIME: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, NavigationActivity::class.java))
            finish()
        }, SPLASHTIME)
    }
}