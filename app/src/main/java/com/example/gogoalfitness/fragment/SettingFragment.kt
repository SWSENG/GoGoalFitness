package com.example.gogoalfitness.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gogoalfitness.EditProfileActivity
import com.example.gogoalfitness.ReminderActivity
import com.example.gogoalfitness.SplashActivity
import com.example.gogoalfitness.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SettingFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }

        user?.let {
            val uid = user.uid
            database = FirebaseDatabase.getInstance().getReference(uid).child("UserInfo")
        }

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

        bind.SignOutCard.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@SettingFragment.requireContext(), SplashActivity::class.java)
            startActivity(intent)
        }

        bind.editBtn.setOnClickListener {
            val intent = Intent(this@SettingFragment.requireContext(), EditProfileActivity::class.java)
            startActivity(intent)

        }

        database.get()
            .addOnSuccessListener {
                    result-> bind.ProfileName.text =
                result.child("username").value.toString()

            }
            .addOnFailureListener{

            }

        return bind.root
    }

}