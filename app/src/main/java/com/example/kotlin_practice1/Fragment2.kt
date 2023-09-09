package com.example.kotlin_practice1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    private lateinit var binding:Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(layoutInflater)
        val nickname = arguments?.getString("login")

        binding.button3.setOnClickListener{
            val password = binding.editTextTextPassword.text.toString()
            if (password.isEmpty())
                Toast.makeText(context, "Enter your password", Toast.LENGTH_SHORT).show()
            else{
                val bundle = Bundle()
                bundle.putString("login", nickname)
                bundle.putString("password", password)
                findNavController().navigate(R.id.action_fragment2_to_fragment3, bundle)
            }

        }
        return binding.root
    }
}