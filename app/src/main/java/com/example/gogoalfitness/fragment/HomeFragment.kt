package com.example.gogoalfitness.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gogoalfitness.LegActivity
import com.example.gogoalfitness.ShoulderActivity
import com.example.gogoalfitness.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate th   e layout for this fragment

        val bind = FragmentHomeBinding.inflate(layoutInflater)

        bind.cardShoulder.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), ShoulderActivity::class.java)
            startActivity(intent)
        }

        bind.cardLeg.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), LegActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }


}