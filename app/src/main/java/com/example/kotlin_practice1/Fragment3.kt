package com.example.kotlin_practice1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment3Binding
import org.koin.androidx.viewmodel.ext.android.viewModel

class Fragment3 : Fragment() {
    private lateinit var binding: Fragment3Binding
    private val vModel : MyViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment3Binding.inflate(layoutInflater)

        binding.buttonFr3OK.setOnClickListener {
            val email = binding.editTextText2.text.toString()
            if (email.isEmpty())
                Toast.makeText(context, "Enter your email", Toast.LENGTH_SHORT).show()
            else{
                vModel.setEmail(email)
                Toast.makeText(context, "Account info has been entered", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fragment3_to_fragment1)
            }
        }
        binding.buttonFr3Back.setOnClickListener {
            findNavController().navigate(R.id.action_fragment3_to_fragment2)
        }
        return binding.root
    }
}