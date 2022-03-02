package com.example.gogoalfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gogoalfitness.fragment.HomeFragment
import com.example.gogoalfitness.fragment.RecipeFragment
import com.example.gogoalfitness.fragment.SettingFragment
import com.example.gogoalfitness.fragment.StatisticFragment
import kotlinx.android.synthetic.main.activity_navigation.*


class NavigationActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val recipeFragment = RecipeFragment()
    private val statisticFragment = StatisticFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(homeFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.ic_Home -> replaceFragment(homeFragment)
                R.id.ic_Recipe -> replaceFragment(recipeFragment)
                R.id.ic_Statistic -> replaceFragment(statisticFragment)
                R.id.ic_Setting -> replaceFragment(settingFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment)
    {
        if(fragment != null)
        {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.Fragment_container, fragment)
            transaction.commit()
        }
    }
}