package com.example.kotlin_practice1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin_practice1.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private lateinit var vModel: viewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(layoutInflater)

        binding.button2.setOnClickListener {
            val str = binding.editTextText.text.toString()
            if (str.isEmpty())
                Toast.makeText(context, "Enter your nickname", Toast.LENGTH_SHORT).show()
            else {
                vModel = ViewModelProvider(requireActivity())[viewModel::class.java]
                vModel.setLogin(str)
                findNavController().navigate(R.id.action_fragment1_to_fragment2)
            }
        }
        return binding.root
    }
}

