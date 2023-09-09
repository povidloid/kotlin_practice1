package com.example.kotlin_practice1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private lateinit var binding:Fragment3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(layoutInflater)
        val nickname = arguments?.getString("login")
        val password = arguments?.getString("password")

        binding.textView.text = "Nickname: $nickname\nPassword: $password"

        binding.buttonThirdFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fragment3_to_fragment1)
        }

        return binding.root
    }

}