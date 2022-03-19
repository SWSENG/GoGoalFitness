package com.example.gogoalfitness.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gogoalfitness.ReminderActivity
import com.example.gogoalfitness.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bind = FragmentSettingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        bind.reminderCard.setOnClickListener {
            val intent = Intent(this@SettingFragment.requireContext(), ReminderActivity::class.java)
            startActivity(intent)
        }

        bind.FacebookLink.setOnClickListener{
            val uri = Uri.parse("https://www.facebook.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        bind.InstaLink.setOnClickListener{
            val uri = Uri.parse("https://www.instagram.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        bind.TwitterLink.setOnClickListener{
            val uri = Uri.parse("https://twitter.com/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        bind.RateUsCard.setOnClickListener{
            val uri = Uri.parse("https://play.google.com/store/apps/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        bind.FeedbackCard.setOnClickListener{
            val uri = Uri.parse("mailto:gogoalFitness@gmail.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        return bind.root
    }

}