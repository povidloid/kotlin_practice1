package com.example.kotlin_practice1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment2Binding

class Fragment2 : Fragment() {
    private lateinit var binding:Fragment2Binding
    private lateinit var vModel:viewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(layoutInflater)
        vModel = ViewModelProvider(requireActivity())[viewModel::class.java]

        binding.buttonFr2Continue.setOnClickListener{
            val password = binding.editTextTextPassword.text.toString()
            if (password.isEmpty())
                Toast.makeText(context, "Enter your password", Toast.LENGTH_SHORT).show()
            else{
                vModel.setPassword(password)
                findNavController().navigate(R.id.action_fragment2_to_fragment3)
            }
        }
        binding.buttonFr2Back.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }
        return binding.root
    }
}